package wanted.subject.recruit.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {
    @Id
    @GeneratedValue
    private Long id;

    private String country;         // 국가
    private String region;          // 지역
    private String position;        // 채용 포지션
    private int reward;             // 채용 보상금
    private String tech;            // 사용 기술
    private String content;         // 채용 내용

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
}
