package wanted.subject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.subject.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
