package ec.nttdatachallenge.challengean.domain;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;
import org.springframework.http.HttpStatus;

import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.infraestructure.exception.ApplicationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@MapperConfig(
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
public interface CommonMappingConfig {

    default CryptocurrencyEnum map(String sourceEnum) throws ApplicationException {
        return Arrays.stream(CryptocurrencyEnum.values())
                .filter(cryptocurrencyEnum -> cryptocurrencyEnum.getDescription().equals(sourceEnum))
                .findFirst()
                .orElseThrow(()->
                        new ApplicationException("400", "Cryptocurrency not found", HttpStatus.BAD_REQUEST));

    }

    default Date stringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    default String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
