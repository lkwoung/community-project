package lkwoung.movie.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findAllByMemberIdAndMemberState(String id, String state);

    List<Member> findAllByMemberStateOrderByMemberId(String state);

    Integer countAllBy();
}
