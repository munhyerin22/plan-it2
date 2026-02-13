package spartaclub.planit2.User.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK용 id

    @Column(length =10, nullable = false)
    private String username; // user 이름

    @Column(unique = true,nullable = false)
    private String email; // 로그인용 email

    @Column(nullable = false)
    private String password; // 로그인용 비밀번호

    public User(String username, String email, String password){ //생성자
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 수정
    public void update(String username) {
        this.username = username;
    } // user 이름만 수정?..

}
