package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    // 기본 생성자
    public Member() {

    }

    // 회원 이름, 나이를 매개변수로 받는 생성자
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
