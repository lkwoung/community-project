package lkwoung.movie.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lkwoung.movie.request.memberReqeust.SignUpRequest;
import lkwoung.movie.request.memberReqeust.UpdateRequest;
import lkwoung.movie.service.MemberService;
import lkwoung.movie.service.ReturnService;
import lkwoung.movie.util.EnumCode;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
@Api(tags = "회원 API")
public class MemberController {

    private final ReturnService gv_returnService;
    private final MemberService gv_memberService;

    @Operation(summary = "회원가입", description = "Id, Password, PhoneNumber, Notion, LogDate, State, Authority")
    @PostMapping
    public JSONObject signup(@RequestBody @Valid SignUpRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return gv_returnService.createResponse(EnumCode.VALID_ERROR,
                    result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        return gv_memberService.userSignUp(request);
    }

    @Operation(summary = "회원목록", description = "list(admin only)")
    @GetMapping
    public JSONObject list() {
        return gv_memberService.userList();
    }

    @Operation(summary = "회원검색", description = "search(admin only)")
    @GetMapping("/{keyword}")
    public JSONObject search(@PathVariable String keyword) {
        return gv_memberService.userSearch(keyword);
    }

    @Operation(summary = "회원수정", description = "update")
    @PatchMapping("/{memberId}")
    public JSONObject update(@PathVariable String memberId, @RequestBody @Valid UpdateRequest request, BindingResult result){
        if (result.hasErrors()) {
            return gv_returnService.createResponse(EnumCode.VALID_ERROR,
                    result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        return gv_memberService.userUpdate(memberId, request);
    }

    @Operation(summary = "회원삭제", description = "delete")
    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable String id){
        return gv_memberService.userDelete(id);
    }

}
