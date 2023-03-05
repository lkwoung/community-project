package lkwoung.movie.service;

import lkwoung.movie.entity.member.Member;
import lkwoung.movie.entity.member.MemberRepository;
import lkwoung.movie.request.memberReqeust.UpdateRequest;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    Member member;
    String id = "testId";
    boolean skipTearDown = false;

    @BeforeEach
    public void setUp() {
        skipTearDown = false;

        member = Member.builder()
                .memberId(id)
                .memberPassword("123456")
                .memberPhoneNumber("010-0000-0000")
                .memberAuthority("member")
                .memberState("active")
                .memberNotion("hello world")
                .memberRegisterDate("2023-03-05 20:49:00")
                .memberLogDate("2023-03-05 20:49:00")
                .build();
    }

    @AfterEach
    public void tearDown(){
        if(!skipTearDown){
            memberRepository.deleteById("testId");
        }
    }
    
    @Test
    @DisplayName("")
    public void MemberSave() {
        // given
        memberRepository.save(member);
        
        // when
        Optional<Member> getMember = memberRepository.findAllByMemberIdAndMemberState(member.getMemberId(), "active");
        Member findMember = getMember.get();

        // then
        Assertions.assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
    }
    
    @Test
    @DisplayName("")
    public void MemberList() {
        // given
        Integer beforeSize = memberRepository.countAllBy();
        memberRepository.save(member);

        // when
        JSONObject jsonObject = memberService.userList();
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray row = (JSONArray)data.get("row");
        int afterSize = row.size();

        // then
        Assertions.assertThat(beforeSize+1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("")
    public void MemberSearch() {
        // given
        memberRepository.save(member);

        // when
        JSONObject jsonObject = memberService.userSearch(id);
        System.out.println("jsonObject = " + jsonObject);

        JSONObject data = (JSONObject) jsonObject.get("data");
        String memberId = (String) data.get("memberId");

        // then
        Assertions.assertThat(member.getMemberId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("")
    public void MemberUpdate() {
        // given
        memberRepository.save(member);

        // when
        String phoneNumber = "010-1111-1111";
        String notion = "go hell";

        UpdateRequest updateRequest = new UpdateRequest(phoneNumber, notion);
        memberService.userUpdate(id, updateRequest);

        // then
        Optional<Member> savedMember = memberRepository.findAllByMemberIdAndMemberState(id, "active");
        Assertions.assertThat(savedMember.get().getMemberPhoneNumber()).isEqualTo(phoneNumber);
        Assertions.assertThat(savedMember.get().getMemberNotion()).isEqualTo(notion);
    }

    @Test
    @DisplayName("")
    public void MemberDelete() {

        skipTearDown = true;

        // given
        memberRepository.save(member);

        // when
        memberService.userDelete(member.getMemberId());
        Optional<Member> findMember = memberRepository.findAllByMemberIdAndMemberState(member.getMemberId(), "active");

        // then
        Assertions.assertThatThrownBy(() -> findMember.get()).isInstanceOf(NoSuchElementException.class);
    }
}