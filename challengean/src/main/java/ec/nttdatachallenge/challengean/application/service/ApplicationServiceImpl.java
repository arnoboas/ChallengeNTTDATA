package ec.nttdatachallenge.challengean.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import ec.nttdatachallenge.challengean.application.dto.SumAccumulator;
import ec.nttdatachallenge.challengean.application.mapper.Mapper;
import ec.nttdatachallenge.challengean.application.port.in.QuoteService;
import ec.nttdatachallenge.challengean.application.port.in.ReportService;
import ec.nttdatachallenge.challengean.application.port.out.cache.QuoteCacheRepository;
import ec.nttdatachallenge.challengean.application.port.out.external.CarModelConnector;
import ec.nttdatachallenge.challengean.application.port.out.external.CryptoCurrencyConnector;
import ec.nttdatachallenge.challengean.application.port.out.persistence.ModelPersistenceRepository;
import ec.nttdatachallenge.challengean.application.port.out.persistence.QuotePersistenceRepository;
import ec.nttdatachallenge.challengean.domain.models.VersionHyundai;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ReqSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.cache.QuoteCacheModel;
import ec.nttdatachallenge.challengean.domain.models.persistence.QuotePersistenceModel;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationError;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements QuoteService, ReportService {

    private final QuoteCacheRepository quoteCacheRepository;

    private final CarModelConnector carModelConnector;

    private final ModelPersistenceRepository modelPersistenceRepository;

    private final QuotePersistenceRepository quotePersistenceRepository;

    private final CryptoCurrencyConnector cryptoCurrencyConnector;


    @Override
    public Mono<ResGQApiModel> getResponseVehicleQuote(ReqGQApiModel requestGenerateQuoteApiModel) {

        return Mono.zip(modelPersistenceRepository.findByName(requestGenerateQuoteApiModel.getModel()),
                        cryptoCurrencyConnector.getCryptoCurrencyPriceByCurrency(requestGenerateQuoteApiModel.getCryptocurrency(), "USD"))
                .onErrorMap(throwable -> new ApplicationException("400", throwable.getMessage(), HttpStatus.BAD_REQUEST))
                .flatMap(objects -> carModelConnector.getCarModelByIdAndModel(objects.getT1().getIdModel(), objects.getT1().getIdVehicle())
                  
                        .map(carModel -> {
                                    BigDecimal priceCrypto = objects.getT2().getPriceUsd();
                                    VersionHyundai version = new VersionHyundai();
                                    version.setModel(requestGenerateQuoteApiModel.getModel());
                                    version.setVersion(carModel.getVersion());
                                    version.setPriceUsd(carModel.getPriceUsd());
                                    version.setPriceCryptocurrency(carModel.getPriceUsd().setScale(3, RoundingMode.HALF_UP).divide(priceCrypto, RoundingMode.HALF_UP));
                                    version.setCryptocurrency(requestGenerateQuoteApiModel.getCryptocurrency().getDescription());
                                    log.info(version.toString());
                                    return version;
                                }
                        )
                        .collectList()
                        .flatMap(carModels -> {
                            QuoteCacheModel data = new QuoteCacheModel();
                            data.setVersions(new HashSet<>(carModels));
                            return quoteCacheRepository.saveQuote(data)
                                    .map(s -> {
                                        data.setConvertionId(s);
                                        return data;
                                    });
                        }))
                .map(quoteCacheModel -> Mapper.INSTANCE.mapToResponseQuoteApiModel(quoteCacheModel));
    }

    @Override
    public Mono<ResSQApiModel> getResponseVehicleOrder(ReqSQApiModel requestSaveQuoteApiModel) {
        return this.quoteCacheRepository.findQuote(requestSaveQuoteApiModel.getConvertionId())
                .switchIfEmpty(Mono.error(ApplicationError.QUOTE_NOT_FOUND))
                .map(quote -> quote.getVersions().stream().filter(version -> version.getVersion().equals(requestSaveQuoteApiModel.getVersion())
                                && version.getCryptocurrency().equals(requestSaveQuoteApiModel.getCryptocurrency())
                                && version.getModel().equals(requestSaveQuoteApiModel.getModel()))
                        .findFirst().orElseThrow(() -> ApplicationError.QUOTE_INCORRECT))
                .switchIfEmpty(Mono.error(ApplicationError.QUOTE_INCORRECT))
                .map(Mapper.INSTANCE::mapToQuotePersistenceModel)
                .flatMap(quotePersistenceModel -> {
                    quotePersistenceModel.setCreateDate(new Date());
                    quotePersistenceModel.setCryptocurrency(requestSaveQuoteApiModel.getCryptocurrency());
                    quotePersistenceModel.setFullName(requestSaveQuoteApiModel.getFullName());
                    return this.quotePersistenceRepository.saveQuotePersistenceModel(quotePersistenceModel);
                })
                .map(quote -> Mapper.INSTANCE.mapToResponseOrderApiModel(quote));

    }

    @Override
    public Flux<ResGRApiModel> getResponseGenerateReportApiModelFlux(ReqGRApiModel requestGenerateQuoteApiModel) {

        BiFunction<SumAccumulator, QuotePersistenceModel, SumAccumulator> accumulator = (sum, item) -> {
            sum.addPriceUsd(item.getPriceUsd());
            sum.addPriceCryptocurrency(item.getPriceCryptocurrency());
            return sum;
        };
        log.info("Inicia proceso generación de reporte {}",requestGenerateQuoteApiModel.toString() );
        return quotePersistenceRepository.findQuotePersistenceModelBy(requestGenerateQuoteApiModel.getModel(), requestGenerateQuoteApiModel.getCryptocurrency().getDescription(), requestGenerateQuoteApiModel.getDate())
                .groupBy(item -> item.getModel() + "#" + item.getVersion() + "#" + item.getCryptocurrency() )
                .flatMap(group -> group
                        .reduce(new SumAccumulator(), accumulator)
                        .map(sum -> new ResGRApiModel(
                                group.key().split("#")[0],
                                group.key().split("#")[1],
                                group.key().split("#")[2],
                                sum.getPriceUsdSum(),
                                sum.getPriceCryptocurrencySum(),
                                requestGenerateQuoteApiModel.getDate()
                        ))
                )
                .map(responseGenerateReportApiModel -> responseGenerateReportApiModel);
    }
}
