package ec.nttdatachallenge.challengean.application.port.in;


import ec.nttdatachallenge.challengean.domain.models.api.ReqGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ReqSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResSQApiModel;
import reactor.core.publisher.Mono;

public interface QuoteService {

    Mono<ResGQApiModel> getResponseVehicleQuote(ReqGQApiModel requestGenerateQuoteApiModel);

    Mono<ResSQApiModel> getResponseVehicleOrder(ReqSQApiModel requestSaveQuoteApiModel);

}
