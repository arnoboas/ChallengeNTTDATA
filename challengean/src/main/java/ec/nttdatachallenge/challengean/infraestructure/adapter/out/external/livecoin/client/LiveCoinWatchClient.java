package ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.livecoin.client;


import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.livecoin.dto.ResponseLiveCoinWatch;
import reactor.core.publisher.Mono;

public interface LiveCoinWatchClient {

    Mono<ResponseLiveCoinWatch> getLiveCoinWatchByFilters(String cryptocurrency, String currency);
}
