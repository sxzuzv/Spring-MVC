package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 회원 저장소에서 현재까지 저장된 전체 회원 목록을 불러와 저장한다.
        List<Member> members = memberRepository.findAll();

        // view의 논리 이름을 매개변수로 하여 ModelView 객체를 생성한다.
        ModelView mv = new ModelView("members");

        // Map<String, Object> => 형식에 맞게 Model에 members List를 넣는다.
        mv.getModel().put("members", members);

        return mv;
    }
}