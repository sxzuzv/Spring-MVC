package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// FrontController Servlet
// urlPatterns: '/front-controller/v1' 하위의 모든 URL 요청에 대하여 FrontControllerServletV1 servlet이 호출된다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    // URL-Controller 매핑 정보를 담아두는 Map을 만든다.
    // key: URL(String), value: URL에 매핑될 Controller(ControllerV1 Interface를 구현)
    private Map<String ,ControllerV1> controllerMap = new HashMap<>();

    // 기본 생성자
    public FrontControllerServletV1() {
        // Map에 매핑 정보를 담는다.: Servlet 실행 시, 자동으로 매핑 정보가 설정되게 한다.
        // 특정 URL에 대한 요청이 오면, 매핑된 Controller를 실행되게 한다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청된 경로를 가져와서(request.getRequestURI()) 저장한다.
        String requestURI = request.getRequestURI();

        // 저장된 경로에 매핑되는 Controller를 매핑 정보에서 찾아 꺼낸 후, 저장한다.
        ControllerV1 controllerV1 = controllerMap.get(requestURI);

        if (controllerV1 == null) { // 예외 처리
            // 매핑되는 Controller 인스턴스가 없는 경우, 상태 코드를 404로 지정한다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 매핑되는 Controller 인스턴스가 존재한다면, 해당 Controller의 process를 실행한다.
        controllerV1.process(request, response);
    }
}