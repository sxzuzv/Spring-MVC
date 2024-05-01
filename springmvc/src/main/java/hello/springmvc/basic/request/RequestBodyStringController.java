package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // Stream은 byte code이며, byte code를 문자로 받을 시 인코딩 형식을 반드시 지정해야 한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("OK!");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException {
        // HttpServletRequest, HttpServletResponse를 통째로 받을 필요는 없으므로,
        // 현재 실행에 필요한 파라미터만 받도록 수정한다.

        // InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회한다.
        // OutputStream(Writer): HTTP 응답 메시지 바디에 직접 결과를 출력한다.

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("OK!");
    }

    /**
     * HttpEntity: HTTP Header, Body 정보를 편리하게 조회할 수 있다.
     * - 메시지 바디 정보를 직접 조회 (@RequestParam, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 응답 시에도 HttpEntity를 사용할 수 있다.
     * - 메시지 바디의 정보를 직접 반환한다. (view 조회 X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity)
            throws IOException {
        // HttpEntity 이용 시, 아래의 코드를 자동으로 수행해준다. (지정한 String 형태로 자동 변환)
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // httpEntity.getBody(): 변환된 Http Message Body의 내용을 가져온다.
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        // 반환 메시지도 HttpEntity를 활용해 작성할 수 있다.
        return new HttpEntity<>("OK!");
    }
}