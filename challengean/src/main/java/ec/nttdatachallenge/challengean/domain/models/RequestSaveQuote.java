package ec.nttdatachallenge.challengean.domain.models;

import java.io.Serializable;

import ec.nttdatachallenge.challengean.domain.models.api.ReqSQApiModel;
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
public class RequestSaveQuote implements Serializable{
	
    private String convertionId;
    private String fullName;
    private String version;
    private String model;
    private String cryptocurrency;

}
