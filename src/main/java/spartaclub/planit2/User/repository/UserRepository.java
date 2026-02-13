package spartaclub.planit2.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spartaclub.planit2.User.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByModifiedAtDesc();//유저 목록 조회?
    List<User> findAllByUsernameOrderByModifiedAtDesc(String name); // 유저 이름으로 조회?

    boolean existsByEmail(String attr0); //이미 가입되어 있는 이메일인지 확인
    Optional<User> findByUsername(String username); //내가 찾는 유저가 있는지 없는지 판별할 때 사용
    // 값이 있을 수도 있고 없을 수도 있다
    // User가 없을 수도 있어서 ->Optional 확정 지어서 없애기
    // 값이 없으면 이렇게 처리해 -> 예외처리
    Optional<User> findByEmail(String email); //유저가 있는지 확인
}
