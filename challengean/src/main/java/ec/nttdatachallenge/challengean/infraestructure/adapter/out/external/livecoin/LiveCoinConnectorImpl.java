package ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.livecoin;


import ec.nttdatachallenge.challengean.application.port.out.external.CryptoCurrencyConnector;
import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.external.CryptocurrencyExtM;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.coinlore.client.CoinLoreClient;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.livecoin.client.LiveCoinWatchClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class LiveCoinConnectorImpl implements CryptoCurrencyConnector {

    private final LiveCoinWatchClient liveCoinWatchConnector;

    private final CoinLoreClient coinLoreClient;

    @Override
    @CircuitBreaker(name = "cryptocurrency-service", fallbackMethod = "getCryptoCurrencyFallback")
    public Mono<CryptocurrencyExtM> getCryptoCurrencyPriceByCurrency(CryptocurrencyEnum cryptocurrency, String currency) {
       return liveCoinWatchConnector.getLiveCoinWatchByFilters(cryptocurrency.getDescription(), currency)
               .map(liveCoinWatchResponse -> CryptocurrencyExtM.builder().priceUsd(liveCoinWatchResponse.getData().getLastPriceUSD()).code(currency).build())
               .doOnSuccess(live-> log.info("Get live coin watch successful price: {}", live.getPriceUsd()))
               .doOnError(throwable -> log.error("Error consume live coin watch: {}" , throwable.getMessage()));
    }

    public Mono<CryptocurrencyExtM> getCryptoCurrencyFallback(CryptocurrencyEnum cryptocurrency, String currency, Throwable throwable) {
        log.info("Fallback Method getCryptoCurrencyFallback");
        return coinLoreClient.getCoinLoreByFilter(cryptocurrency.getCode())
                .next()
                .map(liveCoinWatchResponse -> {
                    log.info(liveCoinWatchResponse.toString());
                    return CryptocurrencyExtM.builder().priceUsd(liveCoinWatchResponse.getPriceUsd()).code(currency).build();
                })
                .doOnSuccess(live-> log.info("Get live coin watch successful {}", live.getPriceUsd()))
                .doOnError(error -> log.error("Error consume live coin watch: {}" , throwable.getMessage()));
    }
}
