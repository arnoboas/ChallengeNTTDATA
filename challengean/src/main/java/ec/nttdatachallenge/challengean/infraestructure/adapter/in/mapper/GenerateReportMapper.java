package ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper;

import ec.nttdatachallenge.challengean.domain.CommonMappingConfig;
import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.RequestGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateReport;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGRApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGRApiModel;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationException;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)

public interface GenerateReportMapper extends CommonMappingConfig {
    GenerateReportMapper INSTANCE = Mappers.getMapper(GenerateReportMapper.class);
    @Mappings({
            @Mapping(source = "data.model", target = "model"),
            @Mapping(source = "data.date", target = "date"),
            @Mapping(source = "data.cryptocurrency", target = "cryptocurrency")
    })
    ReqGRApiModel mapToRequestGenerateReportApiModel(RequestGenerateReport requestGenerateReport);

    default CryptocurrencyEnum map(String sourceEnum) throws ApplicationException {
        return Arrays.stream(CryptocurrencyEnum.values())
                .filter(cryptocurrencyEnum -> cryptocurrencyEnum.getDescription().equals(sourceEnum))
                .findFirst()
                .orElseThrow(()->
                        new ApplicationException("400", "Cryptocurrency not found", HttpStatus.BAD_REQUEST));

    }


    default BigDecimal roundBigDecimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "model", target = "model"),
            @Mapping(source = "cryptocurrency", target = "cryptocurrency"),
            @Mapping(source = "usdAmount", target = "usdAmount"),
            @Mapping(source = "cryptocurrencyAmount", target = "cryptocurrencyAmount")
    })
   ResponseGenerateReport mapToResponseGenerateReportDataInner(ResGRApiModel responseGenerateReportApiModel);

}
