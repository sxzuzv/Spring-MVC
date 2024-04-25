package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // paramMap을 통해 이름, 나이 데이터를 가져와 저장한다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 저장한 데이터로 Member 객체를 생성한다.
        Member member = new Member(username, age);
        // 회원 저장소에 생성한 객체(member)를 저장한다.
        memberRepository.save(member);

        // view의 논리 이름을 매개변수로 하여 ModelView 객체를 생성한다.
        ModelView mv = new ModelView("save-result");

        // Map<String, Object> => 형식에 맞게 Model에 member 객체를 넣는다.
        mv.getModel().put("member", member);

        return mv;
    }
}