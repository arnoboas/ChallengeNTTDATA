package ec.nttdatachallenge.challengean.domain.models.api;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class ResSQApiModel implements Serializable{
	
    private CryptocurrencyEnum cryptocurrency;
    private String date;
    private String priceUsd;
    private String purchaseId;
    private String fullName;
    private String model;
    private BigDecimal priceCryptocurrency;
    private String version;

}
