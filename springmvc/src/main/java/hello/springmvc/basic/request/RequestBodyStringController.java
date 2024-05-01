package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
}