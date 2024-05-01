package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
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

    /**
     * @RequestBody에 직접 만든 객체(HelloData)를 지정할 수 있다.
     * 이때, @RequestBody는 생략 불가하다. -> @ModelAttribute가 적용되기 때문!
     * HttpMessageConverter를 사용하여 V2의 objectMapper 활용 과정을 자동화
     * 요청 메시지가 JSON일 경우, MappingJackson2HttpMessageConverter 사용 (content-type:application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "@k!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        // HttpEntity 사용도 가능하다. (getBody()를 통해 데이터를 꺼내와야 한다.)
        HelloData body = data.getBody();

        log.info("username={}, age={}", body.getUsername(), body.getAge());

        return "@k!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        // 생성된 HelloData 객체가 HTTP Message Converter에 의해 객체로 변경된다.
        // JSON으로 변경된 문자가 HTTP Message 응답 시에도 그대로 나오게 된다.
        return data;    // 요청한 JSON과 동일한 형태의 응답을 확인할 수 있다.
    }
}