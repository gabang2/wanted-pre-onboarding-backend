package wanted.subject.company.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Builder
    public Company(String name) {
        this.name = name;
    }
}
