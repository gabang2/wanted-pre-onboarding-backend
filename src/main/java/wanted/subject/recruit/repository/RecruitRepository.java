package wanted.subject.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.subject.recruit.entity.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
