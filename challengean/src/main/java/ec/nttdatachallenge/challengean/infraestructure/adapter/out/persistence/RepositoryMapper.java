package ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence;

import ec.nttdatachallenge.challengean.domain.models.persistence.CarPesistenceModel;
import ec.nttdatachallenge.challengean.domain.models.persistence.QuotePersistenceModel;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.entity.CatalogServiceModelEntity;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.entity.QuoteEntity;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;



@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface RepositoryMapper {
    RepositoryMapper INSTANCE = Mappers.getMapper(RepositoryMapper.class);
    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "priceUsd", ignore = true)
    })
    CarPesistenceModel mapToCarModel(CatalogServiceModelEntity object);

    QuoteEntity mapToQuote(QuotePersistenceModel quote);

    QuotePersistenceModel mapToQuotePersistenceModel(QuoteEntity quoteEntity);


}
