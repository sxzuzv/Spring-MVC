package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.zip.CheckedOutputStream;

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

        // Header 편의 메서드 활용
        content(response);

        // cookie 객체 생성 및 메서드 활용
        cookie(response);

        response.getWriter().write("OK!");
    }

    private void content(HttpServletResponse response) {
        // response.setHeader() 메서드를 사용하면 Header의 정보를 직접 설정해줘야 했다.
        // 아래의 메서드를 활용하여 각 정보를 편리하게 설정할 수 있다.

        // 기존 방식
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // 편의 메서드 활용
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); 생략 시 자동 생성된다.
    }

    private void cookie(HttpServletResponse response) {
        // response.setHeader()를 활용한 쿠키 지정
        // Set-Cookie: myCookie=good; max-Age=600;
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        // 쿠키 객체 생성 후, 메서드 활용
        Cookie cookie = new Cookie("myCookie", "good"); // 이름, 값 설정
        cookie.setMaxAge(600);  // 600초 동안 유효
        response.addCookie(cookie);
    }
}
