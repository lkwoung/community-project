package lkwoung.movie.request.memberReqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UpdateRequest {

    @JsonProperty("phoneNumber")
    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @JsonProperty("notion")
    private String notion;

    @Builder
    public UpdateRequest(String phoneNumber, String notion) {
        this.phoneNumber = phoneNumber;
        this.notion = notion;
    }
}
