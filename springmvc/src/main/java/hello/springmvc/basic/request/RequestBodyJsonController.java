package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
    // JSON 결과를 파싱해 Java 객체로 변환하려면 JSON 변환 라이브러리(jackson, Gson 등)를 추가해서 사용해야 한다.
    // SpringBoot로 Spring MVC를 선택하면 기본으로 jackson 라이브러리(ObjectMapper)를 함께 제공한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        // jackson 라이브러리를 이용하여 JSON을 객체(HelloData) 형태로 변경하고, 값을 읽는다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("@k!");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody)
            throws IOException {
        // @RequestBody, @ResponseBody 애너테이션을 적용해 코드 간소화

        log.info("messageBody={}", messageBody);

        // jackson 라이브러리를 이용하여 JSON을 객체(HelloData) 형태로 변경하고, 값을 읽는다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "@k!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData)
            throws IOException {
        // @RequestBody에 직접 만든 객체(HelloData)를 지정할 수 있다.
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "@k!";
    }
}