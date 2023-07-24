package ec.nttdatachallenge.challengean.domain.models.api;

import java.io.Serializable;
import java.util.Set;

import ec.nttdatachallenge.challengean.domain.models.VersionHyundai;
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
public class ResGQApiModel implements Serializable{
	
    private String convertionId;
    private String conversionTimelife;
    private Set<VersionHyundai> versions;

}
