package ec.nttdatachallenge.challengean.domain.models.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarPesistenceModel {
	
    private String version;

    private String idModel;

    private String idVehicle;

    private BigDecimal priceUsd;


}
