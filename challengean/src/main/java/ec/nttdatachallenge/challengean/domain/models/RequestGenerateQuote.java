package ec.nttdatachallenge.challengean.domain.models;

import java.io.Serializable;

import ec.nttdatachallenge.challengean.domain.enums.CryptocurrencyEnum;
import ec.nttdatachallenge.challengean.domain.models.api.ReqGQApiModel;
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
public class RequestGenerateQuote implements Serializable {
	
    private String model;
    private CryptocurrencyEnum cryptocurrency;

}
