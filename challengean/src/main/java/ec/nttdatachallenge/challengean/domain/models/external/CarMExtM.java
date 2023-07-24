package ec.nttdatachallenge.challengean.domain.models.external;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarMExtM implements Serializable {

    private String version;

    private BigDecimal priceUsd;

}
