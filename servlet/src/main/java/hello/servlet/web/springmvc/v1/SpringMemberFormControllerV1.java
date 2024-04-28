package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Controller: 해당 애너테이션을 붙인 Controller는 Spring Bean으로 자동 등록된다.
// 또한, Spring MVC에서 애너테이션 기반 Controller로 인식한다.
@Controller
public class SpringMemberFormControllerV1 {
    // @RequestMapping: 해당 URL이 호출되면 애너테이션을 붙인 메서드가 호출된다. (요청 정보를 매핑한다.)
    // RequestMappingHandlerMapping, RequestMappingHandlerAdapter를 사용하게 된다.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() { // 반환형 ModelAndView 유의!
        // 뷰의 논리 이름만 넘겨줘도 뷰 리졸버가 실제 뷰의 이름을 만들어준다.
        return new ModelAndView("new-form");
    }
}