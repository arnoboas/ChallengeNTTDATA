package ec.nttdatachallenge.challengean.infraestructure.adapter.out.external;



import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import ec.nttdatachallenge.challengean.domain.models.external.CarMExtM;
import ec.nttdatachallenge.challengean.infraestructure.adapter.out.external.models.dto.ResponseCarModel;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ExternalMapper {
    ExternalMapper INSTANCE = Mappers.getMapper(ExternalMapper.class);
    @Mappings({
            @Mapping(source ="name",  target= "version")
    })
    CarMExtM mapToCarModelExternal(ResponseCarModel responseCarModel);

}
