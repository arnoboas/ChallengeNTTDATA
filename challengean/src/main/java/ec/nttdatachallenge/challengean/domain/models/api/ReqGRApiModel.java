package ec.nttdatachallenge.challengean.domain.models.api;

import java.io.Serializable;
import java.time.LocalDate;

import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class ReqGRApiModel implements Serializable{

    private String model;
    private CryptocurrencyEnum cryptocurrency;
    private LocalDate date;
}
