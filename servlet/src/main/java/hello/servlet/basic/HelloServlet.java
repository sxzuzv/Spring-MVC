package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 요청명 '/hello'에 대하여 HelloServlet 서블릿이 실행된다.
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet { // HttpServlet 클래스를 상속받는다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HelloServlet 실행 시, service 메서드가 호출된다.
        System.out.println("HelloServlet.service");

        // 웹 브라우저가 HTTP 요청 메시지를 만들어서 서블릿에 던져준다.
        /* WAS 서버는 서블릿 표준 스펙을 구현한다.
           (HttpServletRequest, HttpServletResponse는 interface이다.)
           이를 구현하는 구현체를 request, response를 통해 출력한다.
         */
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 요청명을 통해 입력되는 쿼리 파라미터 값을 request.getParameter()를 사용하여 읽는다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // ContentType, CharacterEncoding: Http 메시지 header에 포함된다.
        // 웹 브라우저에 단순 텍스트 형태로 요청 값을 보낸다.
        response.setContentType("text/plane");
        response.setCharacterEncoding("utf-8");  // 문자 인코딩 정보 세팅한다.

        // response.getWriter().write("내용"); => Http 메시지 body에 지정 데이터가 들어간다.
        response.getWriter().write("hello " + username);
    }
}
