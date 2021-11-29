package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithJob {
    private String name;
    private String job;
}
