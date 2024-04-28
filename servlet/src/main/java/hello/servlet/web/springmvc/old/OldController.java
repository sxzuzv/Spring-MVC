package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

        // 현 상태에서는 뷰의 논리 이름만 가지고는 실제 뷰를 찾을 수 없다.
        // 실행 시, 컨트롤러는 정상 호출되지만 Whitelabel Error Page 오류가 발생한다.

        // application.properties에 prefix, suffix 추가 시, 정상적으로 뷰가 출력된다.
        return new ModelAndView("new-form");
    }
}