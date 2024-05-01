package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // request.getParameter()를 사용해 요청 파라미터 정보를 가져와 저장한다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("OK!");
    }

    @ResponseBody   // HTTP 응답 메시지 Body에 문자열을 그대로 넣어서 반환한다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int memberAge) {

        // @RequestParam 애너테이션을 사용해 파라미터 정보를 가져와 로그를 출력한다.
        log.info("username={}, memberAge={}", username, memberAge);

        return "OK!";
    }
}