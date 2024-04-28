package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 애너테이션 기반의 Controller를 작성한다.
@Controller
public class SpringMemberFormControllerV1 {
    // RequestMappingHandlerMapping, RequestMappingHandlerAdapter를 사용하게 된다.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() { // 반환형 ModelAndView 유의!
        // 뷰의 논리 이름만 넘겨줘도 뷰 리졸버가 실제 뷰의 이름을 만들어준다.
        return new ModelAndView("new-form");
    }
}