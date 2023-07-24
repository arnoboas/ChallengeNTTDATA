package ec.nttdatachallenge.challengean.application.port.out.persistence;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import ec.nttdatachallenge.challengean.domain.models.persistence.QuotePersistenceModel;

public interface QuotePersistenceRepository {
    Mono<QuotePersistenceModel> saveQuotePersistenceModel(QuotePersistenceModel quotePersistenceModel);

    Flux<QuotePersistenceModel> findQuotePersistenceModelBy(String model, String cryptocurrency,  LocalDate date);

}
