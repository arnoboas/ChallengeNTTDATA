package ec.nttdatachallenge.challengean.infraestructure.adapter.out.cache;


import ec.nttdatachallenge.challengean.domain.models.cache.QuoteCacheModel;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.cache.entity.QuoteRedis;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)


public interface CacheMapper {
    CacheMapper INSTANCE = Mappers.getMapper(CacheMapper.class);

    QuoteCacheModel mapToQuoteCacheModel(QuoteRedis quoteRedis);

    @InheritInverseConfiguration
    QuoteRedis mapToQuoteRedis(QuoteCacheModel quote);


}
