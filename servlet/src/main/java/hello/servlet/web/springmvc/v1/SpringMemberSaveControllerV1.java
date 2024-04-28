package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberSaveControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        // request.getParameter()를 통해 이름, 나이 데이터를 가져와 저장한다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 저장한 데이터로 Member 객체를 생성한다.
        Member member = new Member(username, age);
        // 회원 저장소에 생성한 객체(member)를 저장한다.
        memberRepository.save(member);

        // 반환할 ModelAndView 객체를 생성하고 뷰의 논리 이름을 지정한다.
        ModelAndView mv = new ModelAndView("save-result");

        // Map<String, Object> => 형식에 맞게 Model에 member 객체를 넣는다.
        mv.addObject("member", member);

        return mv;
    }
}