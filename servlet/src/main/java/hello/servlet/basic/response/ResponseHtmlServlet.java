package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Content-Type: text/html;charset=utf-8
        // Content-Type 설정 및 한글 깨짐 방지를 위해 인코딩 방식을 지정한다.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        // 웹 브라우저에서 아래 HTML 응답을 렌더링하여 출력한다.
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕하세요!</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
