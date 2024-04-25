package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // v1의 각 Controller에서 수행한 JSP forward 작업을 추출한다.
    public void render(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Model에 있는 데이터를 모두 꺼낸 후, request.setAttribute()를 사용하여 key에 따른 value 값을 모두 저장한다.
        modelToRequestAttribute(model, request);

        // JSP가 forward 될 때, request.getAttribute를 통해 set 해둔 정보를 사용하게 된다.
        // (JSP는 request.setAttribute를 통해 필요한 데이터를 넣어둬야 한다.)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}