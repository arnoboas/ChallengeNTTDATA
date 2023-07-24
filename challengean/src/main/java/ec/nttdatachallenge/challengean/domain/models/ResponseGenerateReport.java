package ec.nttdatachallenge.challengean.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

public class ResponseGenerateReport implements Serializable{
	
    private String model;
    private String version;
    private String cryptocurrency;
    private BigDecimal usdAmount;
    private BigDecimal cryptocurrencyAmount;
    private LocalDate date;
    
	public void data(List<ResponseGenerateReport> responseGenerateReportDataInners) {
		// TODO Auto-generated method stub
		
	}

}
