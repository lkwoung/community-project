package lkwoung.movie.request.memberReqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpRequest {

    @JsonProperty("id")
    @NotEmpty(message = "아이디를 입력해주세요.")
    @NotBlank(message = "아이디에 공백은 사용할 수 없습니다.")
//    @Pattern(regexp = "", message = "")
    private String id;

    @JsonProperty("password")
    @NotEmpty(message = "패스워드를 입력해주세요.")
    @NotBlank(message = "패스워드에 공백은 사용할 수 없습니다.")
//    @Pattern(regexp = "", message = "")
    private String password;

    @JsonProperty("phoneNumber")
    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @JsonProperty("notion")
    private String notion;
}
