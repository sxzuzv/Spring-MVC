package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        // 뷰 템플릿 위치 경로: resources/templates/response/hello.html
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "HELLO!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        // String으로 뷰의 논리 이름을 반환한다. => 뷰에 전달할 데이터를 담을 Model 필요!
        model.addAttribute("data", "@K!");

        // @Controller 애너테이션 사용 + 문자열 반환 => 뷰의 논리 이름으로 판단
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        // 뷰의 논리 이름과 매핑 URL이 동일할 시, 관례적으로 뷰 이름으로 판단한다. (권장 X)
        model.addAttribute("data", "@K!");
    }
}