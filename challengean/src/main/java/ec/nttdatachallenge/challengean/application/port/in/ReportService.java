package ec.nttdatachallenge.challengean.application.port.in;



import ec.nttdatachallenge.challengean.domain.models.api.ReqGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGRApiModel;
import reactor.core.publisher.Flux;

public interface ReportService {

    Flux<ResGRApiModel> getResponseGenerateReportApiModelFlux(ReqGRApiModel saveOrder);

}
