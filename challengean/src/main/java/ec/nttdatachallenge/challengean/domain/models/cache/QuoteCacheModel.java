package ec.nttdatachallenge.challengean.domain.models.cache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ec.nttdatachallenge.challengean.domain.models.VersionHyundai;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteCacheModel {
	
    private String convertionId;
    private String conversionTimelife;
    private Set<VersionHyundai> versions;


}
