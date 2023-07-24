package ec.nttdatachallenge.challengean.domain.models.external;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CryptocurrencyExtM implements Serializable{

    private String code;

    private BigDecimal priceUsd;
}
