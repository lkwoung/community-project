package lkwoung.movie.entity.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
@DynamicUpdate
public class Member {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_phone_number")
    private String memberPhoneNumber;

    @Column(name = "member_authority")
    private String memberAuthority;

    @Column(name = "member_state")
    private String memberState;

    @Column(name = "member_notion")
    private String memberNotion;

    @Column(name = "member_register_date")
    private String memberRegisterDate;

    @Column(name = "member_log_date")
    private String memberLogDate;

    @Builder
    public Member(String memberId, String memberPassword, String memberPhoneNumber, String memberAuthority,
                  String memberState, String memberNotion, String memberRegisterDate, String memberLogDate) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberAuthority = memberAuthority;
        this.memberState = memberState;
        this.memberNotion = memberNotion;
        this.memberRegisterDate = memberRegisterDate;
        this.memberLogDate = memberLogDate;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.memberPhoneNumber = phoneNumber;
    }

    public void changeNotion(String notion) {
        this.memberNotion = notion;
    }
}
