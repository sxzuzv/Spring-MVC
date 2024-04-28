package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 가입 Form
    @GetMapping("/new-form")
    public String newForm() {
        /*
            애너테이션 기반의 Controller는 ModelAndView 반환도 가능하지만
            interface 형태로 설계돼 있어 유연한 활용이 가능하다.
            String 형태로 논리 뷰 이름을 반환해도 프로세스가 정상 실행된다.
         */
        return "new-form";
    }

    // 입력된 회원 정보 저장
    @PostMapping("/save")
    public String process(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        /*
            @RequestParam: 메서드의 매개변수로 요청 파라미터를 직접 받을 수 있다.
            파라미터의 타입 캐스팅도 자동으로 처리해준다. (age: String -> int)
            Model도 파라미터로 받을 수 있다.
         */

        // 파라미터로 넘어온 username, age로 새로운 Member 객체를 생성하고 변수 member에 저장한다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 파라미터로 넘어온 model에 생성한 member 객체를 넣는다.
        model.addAttribute("member", member);

        return "save-result";
    }

    // 전체 회원 정보 리스트 확인
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}