package ec.nttdatachallenge.challengean.infraestructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ec.nttdatachallenge.challengean.application.port.in.QuoteService;
import ec.nttdatachallenge.challengean.application.port.out.cache.QuoteCacheRepository;
import ec.nttdatachallenge.challengean.application.port.out.external.CarModelConnector;
import ec.nttdatachallenge.challengean.application.port.out.external.CryptoCurrencyConnector;
import ec.nttdatachallenge.challengean.application.port.out.persistence.ModelPersistenceRepository;
import ec.nttdatachallenge.challengean.application.port.out.persistence.QuotePersistenceRepository;
import ec.nttdatachallenge.challengean.application.service.ApplicationServiceImpl;

@Configuration
public class BeanQuoteConfiguration {

    @Bean
    QuoteService quoteBeanService(final QuoteCacheRepository quoteCacheRepository, final CarModelConnector carModelConnector, final ModelPersistenceRepository modelRepository, final QuotePersistenceRepository quoteRepository, final CryptoCurrencyConnector cryptoCurrencyConnector){
        return new ApplicationServiceImpl(quoteCacheRepository, carModelConnector, modelRepository, quoteRepository, cryptoCurrencyConnector);
    }
    
}
