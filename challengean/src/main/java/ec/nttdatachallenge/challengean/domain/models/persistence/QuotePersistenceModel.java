package ec.nttdatachallenge.challengean.domain.models.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotePersistenceModel implements Serializable {

    private Integer id;

    private String fullName;

    private Date createDate;

    private String model;

    private String version;

    private String cryptocurrency;

    private BigDecimal priceUsd;

    private BigDecimal priceCryptocurrency;

    private String purchaseId;
}
