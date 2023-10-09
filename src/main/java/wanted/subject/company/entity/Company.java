package wanted.subject.company.entity;

import lombok.*;
import wanted.subject.recruit.entity.Recruit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String name;

    // 연관관계 매핑
    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Recruit> recruits = new ArrayList<>();

    @Builder
    public Company(String name) {
        this.name = name;
    }
}
