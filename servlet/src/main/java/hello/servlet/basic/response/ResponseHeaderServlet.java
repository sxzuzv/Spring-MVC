package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // [status-line] HTTP 응답 코드를 지정한다.
        // 응답 코드는 사전에 정의된 상수로 작성!
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers] header에 들어갈 정보를 설정한다.
        // Content-Type 설정
        response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // 캐시 관련 설정 (아래의 설정은 캐시를 완전히 무효화, 과거 버전의 캐시까지 삭제)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        // 사용자가 원하는 임의의 header를 지정할 수 있다.
        response.setHeader("Soo-header", "soo");

        response.getWriter().write("OK!");
    }
}
