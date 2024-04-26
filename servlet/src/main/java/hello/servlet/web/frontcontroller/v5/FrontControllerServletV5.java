package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// FrontController Servlet
// urlPatterns: '/front-controller/v5' 하위의 모든 URL 요청에 대하여 FrontControllerServletV5 servlet이 호출된다.
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // 기존의 경우, Object 자리에 특정 'ControllerVn' 인터페이스를 지정하여 호환이 불가했다.
    // handler(=Controller) 매핑 정보를 담은 Map을 생성한다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 핸들러 어댑터가 여러 개 있는 목록 중, 필요한 어댑터를 가져와서 사용한다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 기본 생성자: 객체 생성 시, 핸들러 매핑 정보와 핸들러 어댑터 목록이 만들어지도록 한다.
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        // ControllerV3 방식을 지원하는 handlerMappingMap을 작성한다.
        // Map<String, Object> handlerMappingMap: Java 객체의 최상위는 Object이므로, 어떤 객체가 들어와도 담을 수 있다.
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // ControllerV4 방식을 지원하는 handlerMappingMap을 추가한다.
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        // 핸들러 어댑터 목록에 ControllerV3 객체를 호환시킬 수 있는 어댑터를 넣는다.
        handlerAdapters.add(new ControllerV3HandlerAdapter());

        // 핸들러 어댑터 목록에 ControllerV4 객체를 호환시킬 수 있는 어댑터를 넣는다.
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) 핸들러 매핑 정보를 통해 실행할 handler(=Controller)를 가져와 저장한다.
        Object handler = getHanlder(request);

        if (handler == null) { // 예외 처리
            // 매핑되는 handler 인스턴스가 없는 경우, 상태 코드를 404로 지정한다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2) 핸들러 어댑터 목록에서 handler와 호환되는 어댑터를 찾아 가져온다.
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3) 가져온 어댑터에서 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환한다.
        ModelView mv = adapter.handle(request, response, handler);

        // 이때, mv(ModelView 형태인 변수)로 얻을 수 있는 정보는 view의 논리 이름 뿐이므로,
        // 뷰의 논리 이름을 토대로 물리 경로로 변환해주는 기능을 추가해주어야 한다.
        String viewName = mv.getViewName();

        // viewResolver(): 뷰의 논리 이름을 토대로 물리 경로를 만들어준다.
        MyView view = viewResolver(viewName);

        // MyView 객체의 render()를 실행해 JSP를 forward 한다.
        // 뷰가 렌더링 되기 위해서는 Model이 필요하므로 mv.getModel()을 통해 Model을 넘겨준다.
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // 핸들러 어댑터 목록을 돌면서 알맞은 어댑터를 찾는다.
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {  // adapter가 handler를 지원한다면
                return adapter;   // 사용할 어댑터이므로 반환한다.
            }
        }
        // 지원하는 adapter가 없는 경우, 예외를 반환한다.
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHanlder(HttpServletRequest request) {
        // 요청된 경로를 가져와서(request.getRequestURI()) 저장한다.
        String requestURI = request.getRequestURI();

        // 저장된 경로에 매핑되는 handler(=Controller)를 매핑 정보에서 찾아 꺼낸 후, 저장한다.
        // Map<String, Object> handlerMappingMap: 반환 값이 Object임에 주의!
        return handlerMappingMap.get(requestURI);
    }

    private static MyView viewResolver(String viewName) {
        // viewName(논리 이름)을 토대로 뷰의 물리 경로를 만든 후 반환한다.
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}