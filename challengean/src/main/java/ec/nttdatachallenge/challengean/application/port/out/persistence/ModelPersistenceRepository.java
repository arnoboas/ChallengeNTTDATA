package ec.nttdatachallenge.challengean.application.port.out.persistence;


import ec.nttdatachallenge.challengean.domain.models.persistence.CarPesistenceModel;
import reactor.core.publisher.Mono;


public interface ModelPersistenceRepository {
    Mono<CarPesistenceModel> findByName(String name);

}
