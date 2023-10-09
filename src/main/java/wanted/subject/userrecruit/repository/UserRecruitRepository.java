package wanted.subject.userrecruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.subject.userrecruit.entity.UserRecruit;

public interface UserRecruitRepository extends JpaRepository<UserRecruit, Long> {
}
