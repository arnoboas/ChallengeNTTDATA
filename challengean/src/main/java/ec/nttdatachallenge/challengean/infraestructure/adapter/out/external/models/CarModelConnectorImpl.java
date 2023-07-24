package ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.models;



import ec.nttdatachallenge.challengean.application.port.out.external.CarModelConnector;
import ec.nttdatachallenge.challengean.domain.models.external.CarMExtM;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.ExternalMapper;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.models.client.CarModelClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarModelConnectorImpl implements CarModelConnector {


    private final CarModelClient carModelClient;


    @Override
    public Flux<CarMExtM> getCarModelByIdAndModel(String id, String model) {

        return carModelClient.getLiveCoinWatchByFilters(id, model)
                .map(responseCarModel -> ExternalMapper.INSTANCE.mapToCarModelExternal(responseCarModel))
                .doOnComplete(()-> log.info("getLiveCoinWatchByFilters successful"))
                .doOnError(throwable -> log.error("Error getLiveCoinWatchByFilters: {}" , throwable.getMessage()));
    }
}
