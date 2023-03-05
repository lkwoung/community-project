package lkwoung.movie.service;

import lkwoung.movie.entity.member.Member;
import lkwoung.movie.entity.member.MemberRepository;
import lkwoung.movie.request.memberReqeust.UpdateRequest;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    Member member;
    String id = "testId";

    @BeforeEach
    public void tearUp() {
        member = Member.builder()
                .id(id)
                .password("123456")
                .phoneNumber("010-0000-0000")
                .authority("member")
                .state("active")
                .notion("hello world")
                .registerDate("2023-03-05 20:49:00")
                .logDate("2023-03-05 20:49:00")
                .build();
    }

    @AfterEach
    public void tearDown(){
        memberRepository.deleteById("testId");
    }

    @Test
    @DisplayName("")
    public void MemberUpdate() {
        // given
        memberRepository.save(member);

        // when
        String phoneNumber = "010-1111-1111";
        String notion = "go hell";

        UpdateRequest updateRequest = UpdateRequest.builder()
                .phoneNumber(phoneNumber)
                .notion(notion)
                .build();

        memberService.userUpdate(id, updateRequest);

        // then
        Optional<Member> savedMember = memberRepository.findAllByIdAndState(id, "active");
        Assertions.assertThat(savedMember.get().getPhoneNumber()).isEqualTo(phoneNumber);
        Assertions.assertThat(savedMember.get().getNotion()).isEqualTo(notion);
    }
}