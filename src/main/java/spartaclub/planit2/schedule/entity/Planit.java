package spartaclub.planit2.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spartaclub.planit2.User.entity.User;

@Getter
@Entity
@Table(name = "planit2")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Planit extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK용 id

    @Column(length =50, nullable = false)
    private String title; // 일정 이름

    @Column(nullable = false)
    private String content; // 일정 내용

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
    private User user; // 작성자 명


    public Planit(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 수정
    public void update(String title) {
        this.title = title;
    }

}
