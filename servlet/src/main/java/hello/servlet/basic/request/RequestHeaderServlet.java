package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        // HTTP 요청 메시지의 START LINE 정보를 불러온다.
        System.out.println("=== REQUEST-LINE - start ===");

        // GET
        System.out.println("request.getMethod() = " + request.getMethod());

        // HTTP/1.1
        System.out.println("request.getProtocol() = " + request.getProtocol());

        // http
        System.out.println("request.getScheme() = " + request.getScheme());

        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());

        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());

        // username=shin (쿼리 스트링(or 파라미터) 출력)
        System.out.println("request.getQueryString() = " + request.getQueryString());

        // https 사용 유무 판단 (true or false)
        System.out.println("request.isSecure() = " + request.isSecure());

        System.out.println("=== REQUEST-LINE - end ===");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {
        // HTTP 요청 메시지 Header의 모든 정보를 출력한다. (두 가지 방법)

        // 첫 번째 방법 (request.getHeaderNames() 활용)
        Enumeration<String> headerNames = request.getHeaderNames();

        System.out.println("=== Headers - start ===");

        while (headerNames.hasMoreElements()) { // 다음 요소가 존재할 시, 반복문 실행
            // 값을 하나씩 꺼내서 저장해 출력한다.
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + headerName);
        }

        System.out.println("=== Headers - end ===");
        System.out.println();

        System.out.println("=== Headers - start ===");

        // 두 번째 방법 (asIterator() 사용)
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + " : " + headerName));

        System.out.println("=== Headers - end ===");
    }
}
