package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
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

    /**
     * @RequestParam.required
     * 예시) /request-param => username이 없으므로 예외
     * 주의!) /request-param?username= : 빈 문자로 통과된다.
     * 주의!) int age -> null을 int에 입력하는 것은 불가하므로 Integer로 변경해야 한다.
     * 혹은 defaultValue 속성을 사용해도 된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "age", required = false) Integer age) {

        // required 속성이 true로 설정된 파라미터는 필수로 존재해야 함에 유의!
        log.info("username={}, age={}", username, age);

        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam("username") String username,
            @RequestParam(value = "age", defaultValue = "100") int age) {

        // defaultValue 설정 시, 파라미터 값을 직접 입력하지 않아도 기본 값이 설정된다.
        log.info("username={}, age={}", username, age);

        return "OK!";
    }
}