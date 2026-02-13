package spartaclub.planit2.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spartaclub.planit2.schedule.entity.Planit;

import java.util.List;

public interface PlanitRepository extends JpaRepository<Planit, Long> {
    List<Planit> findAllByOrderByModifiedAtDesc();
    List<Planit> findAllByNameOrderByModifiedAtDesc(String name);
}
