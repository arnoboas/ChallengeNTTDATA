package ec.nttdatachallenge.challengean.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import ec.nttdatachallenge.challengean.domain.models.RequestGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.RequestSaveQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.ResponseSaveQuote;
import reactor.core.publisher.Mono;

public interface V1ApiDelegate {

	Mono<ResponseEntity<ResponseGenerateQuote>> postGenerateQuote(Mono<RequestGenerateQuote> requestGenerateQuote,
			ServerWebExchange exchange);

	Mono<ResponseEntity<ResponseSaveQuote>> postSaveQuote(Mono<RequestSaveQuote> requestSaveQuote,
			ServerWebExchange exchange);

	Mono<ResponseEntity<ResponseGenerateReport>> postGenerateReport(Mono<RequestGenerateReport> requestGenerateReport,
			ServerWebExchange exchange);

}
