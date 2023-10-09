package wanted.subject.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.subject.recruitment.entity.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
