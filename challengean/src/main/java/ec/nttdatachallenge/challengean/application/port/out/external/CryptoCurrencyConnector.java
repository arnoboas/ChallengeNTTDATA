package ec.nttdatachallenge.challengean.application.port.out.external;


import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.external.CryptocurrencyExtM;
import reactor.core.publisher.Mono;

public interface CryptoCurrencyConnector {

    Mono<CryptocurrencyExtM> getCryptoCurrencyPriceByCurrency(CryptocurrencyEnum cryptocurrency, String currency);
}
