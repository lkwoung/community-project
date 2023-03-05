package lkwoung.movie.service;

import lkwoung.movie.entity.member.Member;
import lkwoung.movie.entity.member.MemberRepository;
import lkwoung.movie.request.memberReqeust.SignUpRequest;
import lkwoung.movie.request.memberReqeust.UpdateRequest;
import lkwoung.movie.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lkwoung.movie.util.EnumCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReturnService returnService;
    private final DateUtils dateUtils;

    // 비밀번호 해시코드로 변환
    @Transactional
    public JSONObject userSignUp(SignUpRequest request) {
        try {
            log.info("MemberService.userSignUp");

            Optional<Member> lv_existMember = memberRepository.findAllByMemberIdAndMemberState(request.getId(), "active");
            if (lv_existMember.isPresent()) {
                return returnService.createResponse(DUPLICATE_IDENTITY, "사용중인 아이디입니다.", null);
            }

            String lv_now = dateUtils.getNow();
            memberRepository.save(
                    Member.builder()
                            .memberId(request.getId())
                            .memberPassword(request.getPassword())
                            .memberPhoneNumber(request.getPhoneNumber())
                            .memberNotion(request.getNotion())
                            .memberAuthority("member")
                            .memberState("active")
                            .memberRegisterDate(lv_now)
                            .memberLogDate(lv_now)
                            .build()
            );

            Thread.sleep(1000);
            return returnService.createResponse(SUCCESS, "success", null);
        } catch (Exception e) {
            log.error("ERROR MemberService.userSingUp");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }


    public JSONObject userList() {
        try {
            log.info("MemberService.userList");

            List<Member> lv_membersList = memberRepository.findAllByMemberStateOrderByMemberId("active");

            JSONObject lv_response = new JSONObject();
            JSONArray lv_membersJsonArray = new JSONArray();
            for (Member member : lv_membersList) {
                JSONObject lv_memberJsonObject = new JSONObject();
                lv_memberJsonObject.put("memberId", member.getMemberId());
                lv_memberJsonObject.put("memberPhoneNumber", member.getMemberPhoneNumber());
                lv_memberJsonObject.put("memberLogDate", member.getMemberLogDate());
                lv_memberJsonObject.put("memberRegisterDate", member.getMemberRegisterDate());

                lv_membersJsonArray.add(lv_memberJsonObject);
            }
            lv_response.put("row", lv_membersJsonArray);

            return returnService.createResponse(SUCCESS, "success", lv_response);
        } catch (Exception e) {
            log.error("ERROR MemberService.userList");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }

    // querydsl 사용
    public JSONObject userSearch(String keyword) {
        try {
            log.info("MemberService.userSearch");

            Optional<Member> lv_findMember = memberRepository.findAllByMemberIdAndMemberState(keyword, "active");
            if (lv_findMember.isEmpty()) {
                return returnService.createResponse(NO_VALUE, "no value", null);
            }

            JSONObject lv_response = new JSONObject();
            lv_response.put("memberId", lv_findMember.get().getMemberId());
            lv_response.put("memberPhoneNumber", lv_findMember.get().getMemberPhoneNumber());
            lv_response.put("memberLogDate", lv_findMember.get().getMemberLogDate());
            lv_response.put("memberRegisterDate", lv_findMember.get().getMemberRegisterDate());

            return returnService.createResponse(SUCCESS, "success", lv_response);
        } catch (Exception e) {
            log.error("ERROR MemberService.userSearch");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }

    @Transactional
    public JSONObject userUpdate(String id, UpdateRequest request) {
        try {
            log.info("MemberService.userUpdate");

            Optional<Member> lv_findMember = memberRepository.findAllByMemberIdAndMemberState(id, "active");
            if (lv_findMember.isEmpty()) {
                return returnService.createResponse(NO_VALUE, "no value", null);
            }

            Member lv_member = lv_findMember.get();
            lv_member.changePhoneNumber(request.getPhoneNumber());
            lv_member.changeNotion(request.getNotion());

            Thread.sleep(1000);
            return returnService.createResponse(SUCCESS, "success", null);
        } catch (Exception e) {
            log.error("ERROR MemberService.userUpdate");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }

    @Transactional
    public JSONObject userDelete(String id) {
        try {
            log.info("MemberService.userDelete");

            Optional<Member> lv_findMember = memberRepository.findAllByMemberIdAndMemberState(id, "active");
            if (lv_findMember.isEmpty()) {
                return returnService.createResponse(NO_VALUE, "no value", null);
            }

            memberRepository.deleteById(id);

            return returnService.createResponse(SUCCESS, "success", null);
        } catch (Exception e) {
            log.error("ERROR MemberService.userDelete");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return returnService.createResponse(SERVER_ERROR, "서버 에러입니다", null);
        }
    }
}
