package spartaclub.planit2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spartaclub.planit2.entity.Planit;

import java.util.List;

public interface PlanitRepository extends JpaRepository<Planit, Long> {
    List<Planit> findAllByOrderByCreateAtDesc();
    List<Planit> findAllByNameOrderByCreateAtDesc(String name);
}
