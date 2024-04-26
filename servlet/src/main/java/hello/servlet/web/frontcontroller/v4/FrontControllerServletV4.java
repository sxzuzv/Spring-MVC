package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// FrontController Servlet
// urlPatterns: '/front-controller/v4' 하위의 모든 URL 요청에 대하여 FrontControllerServletV4 servlet이 호출된다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    // URL-Controller 매핑 정보를 담아두는 Map을 만든다.
    // key: URL(String), value: URL에 매핑될 Controller(ControllerV4 Interface를 구현)
    private Map<String , ControllerV4> controllerMap = new HashMap<>();

    // 기본 생성자
    public FrontControllerServletV4() {
        // Map에 매핑 정보를 담는다.: Servlet 실행 시, 자동으로 매핑 정보가 설정되게 한다.
        // 특정 URL에 대한 요청이 오면, 매핑된 Controller를 실행되게 한다.
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청된 경로를 가져와서(request.getRequestURI()) 저장한다.
        String requestURI = request.getRequestURI();

        // 저장된 경로에 매핑되는 Controller를 매핑 정보에서 찾아 꺼낸 후, 저장한다.
        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null) { // 예외 처리
            // 매핑되는 Controller 인스턴스가 없는 경우, 상태 코드를 404로 지정한다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // createParamMap(): 모든 파라미터 이름을 대상으로 paramMap을 생성한다.
        Map<String, String> paramMap = createParamMap(request);

        // V4 구조: paramMap + Model이 함께 파라미터로 넘어간다.
        Map<String, Object> model = new HashMap<>();

        // 매핑되는 Controller 인스턴스가 존재한다면, 해당 Controller의 process를 실행한다.
        // process()의 매개변수로 paramMap, Model을 넘겨준다.
        // process() 실행 후 반환된 뷰 이름을 viewName에 저장한다. (Model에 데이터가 담겨서 돌아온다.)
        String viewName = controller.process(paramMap, model);

        // viewResolver(): 뷰의 논리 이름(viewName)을 토대로 물리 경로를 만들어준다.
        MyView view = viewResolver(viewName);

        // MyView 객체의 render()를 실행해 JSP를 forward 한다.
        // 뷰가 렌더링 되기 위해서는 Model이 필요하므로 Model도 함께 넘겨준다.
        view.render(model, request, response);
    }

    private static MyView viewResolver(String viewName) {
        // viewName(논리 이름)을 토대로 뷰의 물리 경로를 만든 후 반환한다.
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        // 넘겨줄 paramMap을 생성한다.
        Map<String, String> paramMap = new HashMap<>();

        // HttpServletRequest의 getParameterNames()를 사용해 모든 파라미터 이름을 가져온다.
        // 가져온 파라미터 이름에 대하여 'paramName'을 Key로 하여 그 값들을 paramMap에 넣는다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}