package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    // 애너테이션 기반의 Spring Controller는 다양한 형태의 파라미터를 받을 수 있다.
    // @RequestHeader: HTTP request Header에 포함될 다중 혹은 특정 내용을 설정한다.
    // => 속성: 필수 값 여부 required, 기본 값 속성 defaultValue
    // @CookieValue: 특정 쿠키를 조회한다.
    // => 속성: 필수 값 여부 required, 기본 값 defaultValue
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);  // HTTP method 조회
        log.info("locale={}", locale);          // Locale 정보 조회
        log.info("headerMap={}", headerMap);    // 모든 HTTP header를 MultiValueMap 형식으로 조회
        log.info("host={}", host);              // 특정 HTTP header 조회
        log.info("cookie={}", cookie);          // 특정 쿠키 조회

        return "OK!";
    }
}