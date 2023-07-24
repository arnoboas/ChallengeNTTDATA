package ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationException;
import ec.nttdatachallenge.challengean.util.MockData;
import reactor.test.StepVerifier;

@SpringBootTest
@EnableR2dbcRepositories
//@Import(H2TestSupport.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenericRepositoryTest {

    @Autowired
    GenericRepository genericRepository;

    @Autowired
    ModelRepository modelRepository;


    @Test
    void findByName() {
        StepVerifier.create(genericRepository.findByName("ACCENT"))
                .consumeNextWith(carPersistenceModel -> Assertions.assertNotNull(carPersistenceModel))
                .verifyComplete();
    }

    @Test
    void findByNameThrow() {
        StepVerifier.create(genericRepository.findByName("demo"))
                .consumeErrorWith(throwable -> Assertions.assertInstanceOf(ApplicationException.class, throwable))
                .verify();
    }

   // @Test
    void saveQuotePersistenceModel() {
        StepVerifier.create(genericRepository.saveQuotePersistenceModel(MockData.getQuotePersistenceModel()))
                .consumeNextWith(quotePersistenceModel -> Assertions.assertNotNull(quotePersistenceModel.getPurchaseId()))
                .verifyComplete();

    }

}