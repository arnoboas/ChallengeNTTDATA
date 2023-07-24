package ec.nttdatachallenge.challengean.domain.models;

import java.math.BigDecimal;

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
public class VersionHyundai {

	    private String model;
	    private String version;
	    private BigDecimal priceUsd;
	    private BigDecimal priceCryptocurrency;
	    private String cryptocurrency;


}
