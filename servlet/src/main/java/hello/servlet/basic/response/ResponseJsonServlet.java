package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    // JSON 데이터를 객체로 변경하기 위해 ObjectMapper를 사용한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Content-Type: application/json
        // content-type을 JSON 형태로 지정한다.
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        Data data = new Data();
        data.setUsername("soo");
        data.setAge(20);

        // 데이터를 JSON 문자열로 변경한다. => {"username":"soo", "age":20}
        // objectMapper.writeValueAsString(): 객체의 값을 JSON 문자열로 변경한다.
        String result = objectMapper.writeValueAsString(data);

        // JSON 문자열 값을 응답 메시지로 출력한다.
        response.getWriter().write(result);
    }
}
