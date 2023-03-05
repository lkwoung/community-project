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
    private String id;

    @Column
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String authority;

    @Column
    private String state;

    @Column
    private String notion;

    @Column(name = "register_date")
    private String registerDate;

    @Column(name = "log_date")
    private String logDate;

    @Builder
    public Member(String id, String password, String phoneNumber, String authority,
                  String state, String notion, String registerDate, String logDate) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
        this.state = state;
        this.notion = notion;
        this.registerDate = registerDate;
        this.logDate = logDate;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeNotion(String notion) {
        this.notion = notion;
    }


}
