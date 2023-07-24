package ec.nttdatachallenge.challengean.domain.models.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class ResGRApiModel implements Serializable {
	
	
    private String model;
    private String version;
    private String cryptocurrency;
    private BigDecimal usdAmount;
    private BigDecimal cryptocurrencyAmount;
    private LocalDate date;

}
