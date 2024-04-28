package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        // 회원 저장소에서 현재까지 저장된 전체 회원 목록을 불러와 저장한다.
        List<Member> members = memberRepository.findAll();

        // view의 논리 이름을 매개변수로 하여 ModelAndView 객체를 생성한다.
        ModelAndView mv = new ModelAndView("members");

        // Map<String, Object> => 형식에 맞게 Model에 members List를 넣는다.
        mv.addObject("members", members);

        return mv;
    }
}