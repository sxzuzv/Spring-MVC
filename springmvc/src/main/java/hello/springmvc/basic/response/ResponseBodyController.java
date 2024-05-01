package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("@K!");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("@K!", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        // @ResponseBody: 반환 문자열을 HTTP 응답 Message Body에 넣는다.
        return "@K!";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();

        helloData.setUsername("soo");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        // v2 방식의 경우, 반환 형태를 HelloData 객체로 설정해 코드 간소화를 진행하였다.
        // 그러나 v1 방식과는 달리 HTTP 상태 코드를 지정할 수가 없다.
        // 이를 해결하기 위해 @ResponseStatus 애너테이션이 제공되며, 이를 통해 HTTP 상태 코드 설정이 가능하다.
        HelloData helloData = new HelloData();

        helloData.setUsername("soo");
        helloData.setAge(20);

        return helloData;
    }
}