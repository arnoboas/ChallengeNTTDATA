package ec.nttdatachallenge.challengean.infraestructure.adapter.in.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import ec.nttdatachallenge.challengean.application.port.in.QuoteService;
import ec.nttdatachallenge.challengean.application.port.in.ReportService;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.RequestSaveQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.ResponseSaveQuote;
import ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper.GenerateQuoteMapper;
import ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper.GenerateReportMapper;
import ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper.SaveQuoteMapper;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationError;
import ec.nttdatachallenge.challengean.server.V1ApiDelegate;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VehicleRest implements V1ApiDelegate {

    private final QuoteService quoteService;

    private final ReportService reportService;

    private final GenerateQuoteMapper quoteMapper;

    private final SaveQuoteMapper orderMapper;

    @Override
    public Mono<ResponseEntity<ResponseGenerateQuote>> postGenerateQuote(Mono<RequestGenerateQuote> requestGenerateQuote,
                                                                         ServerWebExchange exchange) {
        return requestGenerateQuote.flatMap(request -> quoteService.getResponseVehicleQuote(quoteMapper.mapToRequestQuoteApiModel(request)))
                .map(quoteMapper::mapToResponseGenerateQuote)
                .map(ResponseEntity::ok)
                ;
    }

    @Override
    public Mono<ResponseEntity<ResponseSaveQuote>> postSaveQuote(Mono<RequestSaveQuote> requestSaveQuote,
                                                                 ServerWebExchange exchange) {
        return requestSaveQuote.flatMap(request -> quoteService.getResponseVehicleOrder(orderMapper.mapToRequestOrderApiModel(request)))
                .map(orderMapper::mapToResponseSaveQuote)
                .map(responseSaveQuote -> ResponseEntity.status(HttpStatus.CREATED).body(responseSaveQuote))
                ;
    }

    @Override
    public Mono<ResponseEntity<ResponseGenerateReport>> postGenerateReport(Mono<RequestGenerateReport> requestGenerateReport,
                                                                           ServerWebExchange exchange) {
        return requestGenerateReport.map(requestGenerateReport1 -> reportService.getResponseGenerateReportApiModelFlux(GenerateReportMapper.INSTANCE.mapToRequestGenerateReportApiModel(requestGenerateReport1)))
               .map(responseGenerateReportApiModelFlux -> responseGenerateReportApiModelFlux
                        .map(responseGenerateReportApiModel -> GenerateReportMapper.INSTANCE.mapToResponseGenerateReportDataInner(responseGenerateReportApiModel))
                        .switchIfEmpty(Mono.error(ApplicationError.NOT_CONTENT)).collectList()
                )
                .flatMap(listMono -> listMono)
                .map(responseGenerateReportDataInners ->
                    {

                        ResponseGenerateReport response = new ResponseGenerateReport();
                        response.data(responseGenerateReportDataInners);
                        return response;
                    })
                .map(ResponseEntity::ok);
    }
}
