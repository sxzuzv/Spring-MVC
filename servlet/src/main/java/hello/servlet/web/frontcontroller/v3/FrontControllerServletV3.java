package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// FrontController Servlet
// urlPatterns: '/front-controller/v3' 하위의 모든 URL 요청에 대하여 FrontControllerServletV3 servlet이 호출된다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    // URL-Controller 매핑 정보를 담아두는 Map을 만든다.
    // key: URL(String), value: URL에 매핑될 Controller(ControllerV3 Interface를 구현)
    private Map<String , ControllerV3> controllerMap = new HashMap<>();

    // 기본 생성자
    public FrontControllerServletV3() {
        // Map에 매핑 정보를 담는다.: Servlet 실행 시, 자동으로 매핑 정보가 설정되게 한다.
        // 특정 URL에 대한 요청이 오면, 매핑된 Controller를 실행되게 한다.
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청된 경로를 가져와서(request.getRequestURI()) 저장한다.
        String requestURI = request.getRequestURI();

        // 저장된 경로에 매핑되는 Controller를 매핑 정보에서 찾아 꺼낸 후, 저장한다.
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) { // 예외 처리
            // 매핑되는 Controller 인스턴스가 없는 경우, 상태 코드를 404로 지정한다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // createParamMap(): 모든 파라미터 이름을 대상으로 paramMap을 생성한다.
        Map<String, String> paramMap = createParamMap(request);

        // 매핑되는 Controller 인스턴스가 존재한다면, 해당 Controller의 process를 실행한다.
        // process()의 매개변수로 파라미터 이름과 값이 들어간 paramMap을 넘겨준다.
        // process() 실행 후 반환된 ModelView 객체를 변수 mv에 저장한다.
        ModelView mv = controller.process(paramMap);

        // 이때, mv(ModelView 형태인 변수)로 얻을 수 있는 정보는 view의 논리 이름 뿐이므로,
        // 뷰의 논리 이름을 토대로 물리 경로로 변환해주는 기능을 추가해주어야 한다.
        String viewName = mv.getViewName();

        // viewResolver(): 뷰의 논리 이름을 토대로 물리 경로를 만들어준다.
        MyView view = viewResolver(viewName);

        // MyView 객체의 render()를 실행해 JSP를 forward 한다.
        // 뷰가 렌더링 되기 위해서는 Model이 필요하므로 mv.getModel()을 통해 Model을 넘겨준다.
        view.render(mv.getModel(), request, response);
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