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
    private String username; // 일정 이름

    @Column(unique = true,nullable = false)
    private String email; // 일정 내용

    @Column(nullable = false)
    private String password;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 수정
    public void update(String username) {
        this.username = username;
    }

}
