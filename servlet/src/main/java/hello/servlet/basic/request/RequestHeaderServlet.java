package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
        printHeaderUtils(request);
        printEtc(request);
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

        // Header의 특정 정보를 지정하여 확인할 수도 있다.
        String host = request.getHeader("host");  // Host 정보를 확인한다.
        System.out.println("host : " + host);

        System.out.println("=== Headers - end ===");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        // HTTP 요청 메시지 Header에 있는 정보를 편리하게 조회한다.
        System.out.println("=== Header 편의 조회 start ===");

        System.out.println("[Host 편의 조회]");

        // Host 정보
        System.out.println("request.getServerName() = " + request.getServerName());

        // port number
        System.out.println("request.getServerPort() = " + request.getServerPort());

        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        // Header의 Locale 정보를 모두 꺼내서 출력한다. (getLocales() 사용)
        // 웹 브라우저 언어 설정에 따라 순서가 변경될 수 있다.
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        // 가장 우선 순위가 높은 언어를 출력한다. (getLocale() 사용)
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        // HTTP Header에 담기는 쿠키 정보를 조회한다.
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        // HTTP 요청 메시지의 Body에 데이터가 존재하는 경우, null 값이 아닌 데이터가 출력된다.
        System.out.println("request.getContentType() : " + request.getContentType());
        System.out.println("request.getContentLength() : " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() : " + request.getCharacterEncoding());

        System.out.println("=== Header 편의 조회 end ===");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {
        // 내부 네트워크 연결과 관련된 정보를 확인한다.
        System.out.println("=== 기타 정보 조회 start ===");

        System.out.println("[Remote 정보]");
        // 요청에 대한 상세 정보를 확인한다.
        System.out.println("request.getRemoteHost() : " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() : " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() : " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        // 현재 내 서버에 대한 정보를 확인한다.
        System.out.println("request.getLocalName() : " + request.getLocalName());
        System.out.println("request.getLocalAddr() : " + request.getLocalAddr());
        System.out.println("request.getLocalPort() : " + request.getLocalPort());
        System.out.println();

        System.out.println("=== 기타 정보 조회 end ===");
        System.out.println();
    }
}
