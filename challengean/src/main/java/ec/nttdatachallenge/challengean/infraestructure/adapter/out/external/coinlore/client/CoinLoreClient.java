package ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.coinlore.client;


import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.coinlore.dto.ResponseCoinLore;
import reactor.core.publisher.Flux;

public interface CoinLoreClient {

    Flux<ResponseCoinLore> getCoinLoreByFilter(String codeCrypto);
}
