package ec.nttdatachallenge.challengean.application.port.out.external;


import ec.nttdatachallenge.challengean.domain.models.external.CarMExtM;
import reactor.core.publisher.Flux;

public interface CarModelConnector {

    Flux<CarMExtM> getCarModelByIdAndModel(String id, String model);
}
