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
}