package wanted.subject.userrecruit.entity;

import lombok.*;
import wanted.subject.company.entity.Company;
import wanted.subject.recruit.dto.RecruitDetailResponseDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.user.entity.User;

import javax.persistence.*;
import java.util.Optional;



@Entity
@Getter
@AllArgsConstructor
public class UserRecruit {
    @Id
    @GeneratedValue
    @Column(name = "user_recruit_id")
    private Long id;

    // 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    public UserRecruit() {}

    //== 연관관계 매핑 ==//
    public void setUser(User user) {
        if (this.user != null) {
            if (this.user.getUserRecruits().contains(this)) {
                this.user.getUserRecruits().remove(this);
            }
        }
        this.user = Optional.ofNullable(user).orElse(this.user);
        this.user.getUserRecruits().add(this);
    }

    public void setRecruit(Recruit recruit) {
        if (this.recruit != null) {
            if (this.recruit.getUserRecruits().contains(this)) {
                this.recruit.getUserRecruits().remove(this);
            }
        }
        this.recruit = Optional.ofNullable(recruit).orElse(this.recruit);
        this.recruit.getUserRecruits().add(this);
    }
}
