package wanted.subject.recruit.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.subject.company.entity.Company;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {
    @Id
    @GeneratedValue
    @Column(name = "recruit_id")
    private Long id;

    private String country;         // 국가
    private String region;          // 지역
    private String position;        // 채용 포지션
    private int reward;             // 채용 보상금
    private String tech;            // 사용 기술
    private String content;         // 채용 내용

    // 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * 생성자
     * @param country
     * @param region
     * @param position
     * @param reward
     * @param tech
     * @param content
     */
    @Builder
    public Recruit(String country, String region, String position, int reward, String tech, String content) {
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.tech = tech;
        this.content = content;
    }

    //== 연관관계 매핑 ==//
    public void setCompany(Company company) {
        if (this.company != null) {
            if (this.company.getRecruits().contains(this)) {
                this.company.getRecruits().remove(this);
            }
        }
        this.company = Optional.ofNullable(company).orElse(this.company);
        this.company.getRecruits().add(this);
    }
}
