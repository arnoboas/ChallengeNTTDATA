package ec.nttdatachallenge.challengean.infraestructure.adapter.in.mapper;

import ec.nttdatachallenge.challengean.domain.models.RequestSaveQuote;
import ec.nttdatachallenge.challengean.domain.models.ResponseSaveQuote;
import ec.nttdatachallenge.challengean.domain.models.api.ReqSQApiModel;
import ec.nttdatachallenge.challengean.domain.models.api.ResSQApiModel;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface SaveQuoteMapper {
    SaveQuoteMapper INSTANCE = Mappers.getMapper(SaveQuoteMapper.class);

    @Mapping(source ="data.convertionId",  target= "convertionId")
    @Mapping(source ="data.fullName",  target= "fullName")
    @Mapping(source ="data.version",  target= "version")
    @Mapping(source ="data.model",  target= "model")
    @Mapping(source = "data.cryptocurrency", target = "cryptocurrency")
    ReqSQApiModel mapToRequestOrderApiModel(RequestSaveQuote requestSaveQuote);



    @Mapping(target= "data.cryptocurrency", source = "cryptocurrency.description")
    @Mapping(target = "data.date", source = "date")
    @Mapping(target = "data.priceUsd", source = "priceUsd")
    @Mapping(target = "data.purchaseId", source = "purchaseId")
    @Mapping(target = "data.fullName", source = "fullName")
    @Mapping(target = "data.model", source = "model")
    @Mapping(target = "data.priceCryptocurrency", source = "priceCryptocurrency")
    @Mapping(target = "data.version", source = "version")
    ResponseSaveQuote mapToResponseSaveQuote(ResSQApiModel responseOrderApiModel);

}
