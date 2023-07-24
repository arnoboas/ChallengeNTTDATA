package ec.nttdatachallenge.challengean.application.dto;


import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class SumAccumulator {
    private BigDecimal priceUsdSum = BigDecimal.ZERO;
    private BigDecimal priceCryptocurrencySum = BigDecimal.ZERO;

    public void addPriceUsd(BigDecimal price) {
        priceUsdSum = priceUsdSum.add(price);
    }

    public void addPriceCryptocurrency(BigDecimal price) {
        priceCryptocurrencySum = priceCryptocurrencySum.add(price);
    }

}