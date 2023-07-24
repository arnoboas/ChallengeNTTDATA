package ec.nttdatachallenge.challengean.domain.models.api;

import java.io.Serializable;

import lombok.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class ReqSQApiModel implements Serializable {
	
    private String convertionId;
    private String fullName;
    private String version;
    private String model;
    private String cryptocurrency;

}
