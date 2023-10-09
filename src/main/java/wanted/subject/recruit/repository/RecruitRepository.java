package wanted.subject.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted.subject.recruit.entity.Recruit;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    @Query("select r from Recruit r "
            + "where r.company.name like concat('%', :search, '%')"
            + "or r.country like concat('%', :search, '%')"
            + "or r.region like concat('%', :search, '%')"
            + "or r.position like concat('%', :search, '%')"
            + "or r.tech like concat('%', :search, '%')"
            + "or r.content like concat('%', :search, '%')")
    List<Recruit> findBySearch(@Param("search") String search);

}
