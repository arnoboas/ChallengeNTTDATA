package ec.nttdatachallenge.challengean.domain.models;

import java.io.Serializable;
import java.util.Set;

import ec.nttdatachallenge.challengean.domain.models.api.ResGQApiModel;
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
public class ResponseGenerateQuote implements Serializable{

    private String convertionId;
    private String conversionTimelife;
    private Set<VersionHyundai> versions;

}
