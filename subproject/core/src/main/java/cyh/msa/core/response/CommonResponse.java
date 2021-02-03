package cyh.msa.core.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {

    private String message;
    private int status;
    private String code;

}
