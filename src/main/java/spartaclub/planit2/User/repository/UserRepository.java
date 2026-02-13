package spartaclub.planit2.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spartaclub.planit2.User.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByModifiedAtDesc();
    List<User> findAllByNameOrderByModifiedAtDesc(String name);

    boolean existsByEmail(String attr0);
    Optional<User> findByUsername(String username);
    // 값이 있을 수도 있고 없을 수도 있다
    // User가 없을 수도 있어서 ->Optional 확정 지어서 없애기
    // 값이 없으면 이렇게 처리해 -> 예외처리
    Optional<User> findByEmail(String email);
}
