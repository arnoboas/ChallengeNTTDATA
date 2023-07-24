package ec.nttdatachallenge.challengean.application.port.out.cache;

import ec.nttdatachallenge.challengean.domain.models.cache.QuoteCacheModel;
import reactor.core.publisher.Mono;

public interface QuoteCacheRepository {

    Mono<String> saveQuote(QuoteCacheModel quote);

    Mono<QuoteCacheModel> findQuote(String convertionId);
}
