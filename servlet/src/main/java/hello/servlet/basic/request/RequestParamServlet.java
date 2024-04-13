package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=soo&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 쿼리 스트링의 전체 파라미터를 조회한다.
        System.out.println("[전체 파라미터 조회] - start");

        // paramName은 Key의 역할이며, Key에 따른 value를 뽑아야 한다.
        // 예시) paramName: username, request.getParameter(paramName): soo
        request.getParameterNames().asIterator()
            .forEachRemaining(
                    paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        // 쿼리 스트링의 단일 파라미터를 조회한다.
        System.out.println("[단일 파라미터 조회] - start");

        // 확인하고자 하는 파라미터를 지정하여 값을 읽어온다.
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username: " + username);
        System.out.println("age: " + age);

        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        // 하나의 파라미터에 대해 여러 개의 값이 넘어갈 수 있다.
        // 이 경우, 내부 우선순위에서 높은 순서로 값이 출력된다.
        System.out.println("[이름이 같은 복수 파라미터 조회] - start");

        // username 파라미터에 대한 여러 개의 값을 String 타입의 배열로 저장한다.
        String[] usernames = request.getParameterValues("username");

        // 배열에 담긴 값들을 차례로 출력한다.
        for (String name : usernames) {
            System.out.println("username : " + name);
        }

        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();
    }
}
