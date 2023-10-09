package wanted.subject.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.subject.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
