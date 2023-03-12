package lkwoung.movie.config;

import lkwoung.movie.entity.member.Member;
import lkwoung.movie.entity.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository gv_memberRepository;

    public CustomUserDetailService(MemberRepository gv_memberRepository) {
        this.gv_memberRepository = gv_memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id){
        try {
            log.info("loaduserByUsername : " + id);
            Optional<Member> lv_user = gv_memberRepository.findAllByMemberIdAndMemberState(id, "active");

            if (lv_user.isPresent()) {
                List<String> lv_list = new ArrayList<>();
                switch (lv_user.get().getMemberAuthority()) {
                    case "admin":
                    {
                        lv_list.add("admin");
                        return new User(id, lv_user.map(Member::getMemberPassword).orElse(null), authorities(new ArrayList<>(lv_list)));
                    }
                    case "member":
                    {
                        lv_list.add("member");
                        return new User(id, lv_user.map(Member::getMemberPassword).orElse(null), authorities(new ArrayList<>(lv_list)));
                    }
                    case "guest":
                    {
                        lv_list.add("guest");
                        return new User(id, lv_user.map(Member::getMemberPassword).orElse(null), authorities(new ArrayList<>(lv_list)));
                    }
                }
            }
            throw new NullPointerException();
        } catch (NullPointerException e) {
            log.error("loadUserByUsername ERROR No Such Element");
            return null;
        }
    }

    public Collection<? extends GrantedAuthority> authorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
