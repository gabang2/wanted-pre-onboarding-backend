package wanted.subject.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wanted.subject.recruit.entity.Recruit;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
