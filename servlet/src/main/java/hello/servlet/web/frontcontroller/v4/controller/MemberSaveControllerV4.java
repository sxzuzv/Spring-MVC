package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // paramMap에서 입력받은 데이터를 가져와 저장한다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 저장한 회원 데이터를 통해 새로운 Member 객체를 생성하고 변수 member에 저장한다.
        Member member = new Member(username, age);
        // 회원 저장소에 생성한 Member 객체를 저장한다.
        memberRepository.save(member);

        // 파라미터로 넘어온 Model에 member 객체를 넣는다.
        model.put("member", member);

        // 뷰 이름을 반환한다.
        return "save-result";
    }
}