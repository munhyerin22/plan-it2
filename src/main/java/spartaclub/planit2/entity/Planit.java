package spartaclub.planit2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String name; // 작성자 명


    public Planit(String title, String content, String name){
        this.title = title;
        this.content = content;
        this.name = name;
    }

    // 수정
    public void update(String title, String name) {
        this.title = title;
        this.name = name;
    }

}
