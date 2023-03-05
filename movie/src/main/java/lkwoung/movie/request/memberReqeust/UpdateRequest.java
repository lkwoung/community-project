package lkwoung.movie.request.memberReqeust;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UpdateRequest {

    @JsonProperty("memberPhoneNumber")
    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String memberPhoneNumber;

    @JsonProperty("memberNotion")
    private String memberNotion;

    @JsonCreator
    public UpdateRequest(@JsonProperty("memberPhoneNumber") String memberPhoneNumber,
                         @JsonProperty("memberNotion") String memberNotion) {
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberNotion = memberNotion;
    }

}
