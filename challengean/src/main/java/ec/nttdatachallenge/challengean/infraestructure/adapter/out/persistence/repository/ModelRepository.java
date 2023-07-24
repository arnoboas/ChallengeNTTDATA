package ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.entity.CatalogServiceModelEntity;
import reactor.core.publisher.Mono;

@Repository
public interface ModelRepository extends ReactiveCrudRepository<CatalogServiceModelEntity, Long> {

    Mono<CatalogServiceModelEntity> findByName(String name);

}
