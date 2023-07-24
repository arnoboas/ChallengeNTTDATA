package ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper;


import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import ec.nttdatachallenge.challengean.domain.models.RequestGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseGenerateQuote;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;


@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)

public interface GenerateQuoteMapper {
    GenerateQuoteMapper INSTANCE = Mappers.getMapper(GenerateQuoteMapper.class);

    @Mapping(source ="data.model.value",  target= "model")
    @Mapping(source = "data.cryptocurrency", target = "cryptocurrency")
    ReqGQApiModel mapToRequestQuoteApiModel(RequestGenerateQuote requestVehicleQuote);
/*
    default CryptocurrencyEnum map(String sourceEnum) throws ApplicationException {
        return Arrays.stream(CryptocurrencyEnum.values())
                .filter(cryptocurrencyEnum -> cryptocurrencyEnum.getDescription().equals(sourceEnum))
                .findFirst()
                .orElseThrow(()->
                        new ApplicationException("400", "Cryptocurrency not found", HttpStatus.BAD_REQUEST));

    }
*/
    @Mapping(target= "data.convertionId", source = "convertionId")
    @Mapping(target = "data.conversionTimelife", source = "conversionTimelife")
    @Mapping(target = "data.versions", source = "versions")
    ResponseGenerateQuote mapToResponseGenerateQuote(ResGQApiModel responseQuoteApiModel);

}
