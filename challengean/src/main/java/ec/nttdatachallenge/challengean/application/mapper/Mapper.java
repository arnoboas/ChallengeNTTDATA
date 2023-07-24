package ec.nttdatachallenge.challengean.application.mapper;


import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.VersionHyundai;
import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.cache.QuoteCacheModel;
import ec.nttdatachallenge.challengean.domain.models.persistence.QuotePersistenceModel;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);
    @Mappings({
            @Mapping(source = "model", target = "model"),
            @Mapping(source = "version", target = "version"),
            @Mapping(source = "priceUsd", target = "priceUsd"),
            @Mapping(source = "priceCryptocurrency", target = "priceCryptocurrency"),
            @Mapping(source = "cryptocurrency", target = "cryptocurrency"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "fullName", ignore = true),
            @Mapping(target = "purchaseId", ignore = true),
            @Mapping(target = "createDate", ignore = true)
    })
    QuotePersistenceModel mapToQuotePersistenceModel(VersionHyundai version);

    @Mappings({
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "model", target = "model"),
            @Mapping(source = "version", target = "version"),
            @Mapping(source = "priceUsd", target = "priceUsd"),
            @Mapping(source = "purchaseId", target = "purchaseId"),
            @Mapping(source = "createDate", target = "date"),
            @Mapping(source = "priceCryptocurrency", target = "priceCryptocurrency"),
            @Mapping(source = "cryptocurrency", target = "cryptocurrency")
    })
    ResSQApiModel mapToResponseOrderApiModel(QuotePersistenceModel quotePersistenceModel);

    default CryptocurrencyEnum map(String sourceEnum) throws ApplicationException {
        return Arrays.stream(CryptocurrencyEnum.values())
                .filter(cryptocurrencyEnum -> cryptocurrencyEnum.getDescription().equals(sourceEnum))
                .findFirst()
                .orElseThrow(()->
                        new ApplicationException("400", "Cryptocurrency not found", HttpStatus.BAD_REQUEST));

    }

    default String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        return sdf.format(date);
    }

    @Mappings({
            @Mapping(source = "convertionId", target = "convertionId"),
            @Mapping(source = "conversionTimelife", target = "conversionTimelife"),
            @Mapping(source = "versions", target = "versions")
    })
    ResGQApiModel mapToResponseQuoteApiModel(QuoteCacheModel quoteCacheModel);

}
