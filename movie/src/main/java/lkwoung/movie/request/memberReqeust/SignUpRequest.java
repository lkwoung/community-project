package lkwoung.movie.request.memberReqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class SignUpRequest {

    @JsonProperty("memberId")
    @NotEmpty(message = "아이디를 입력해주세요.")
    @NotBlank(message = "아이디에 공백은 사용할 수 없습니다.")
//    @Pattern(regexp = "", message = "")
    private String memberId;

    @JsonProperty("memberPassword")
    @NotEmpty(message = "패스워드를 입력해주세요.")
    @NotBlank(message = "패스워드에 공백은 사용할 수 없습니다.")
//    @Pattern(regexp = "", message = "")
    private String memberPassword;

    @JsonProperty("memberPhoneNumber")
    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String memberPhoneNumber;

    @JsonProperty("memberNotion")
    private String memberNotion;
}
