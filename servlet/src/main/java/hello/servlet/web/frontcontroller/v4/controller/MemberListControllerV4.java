package hello.servlet.web.frontcontroller.v4.controller;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 회원 저장소에서 현재까지 저장된 모든 회원 정보를 가져와 List에 저장한다.
        List<Member> members = memberRepository.findAll();

        // 파라미터로 받아온 Model에 회원 List(members)를 넣는다.
        model.put("members", members);

        // 뷰 이름을 반환한다.
        return "members";
    }
}