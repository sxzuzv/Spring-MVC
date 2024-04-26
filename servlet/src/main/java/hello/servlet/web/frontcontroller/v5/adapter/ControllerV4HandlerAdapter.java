package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ControllerV4 방식의 호환을 돕는 HandlerAdapter를 구현한다.
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // 매개변수로 넘어온 handler가 ControllerV4의 인스턴스인지를 판단한다. => true or false
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        // 매개변수로 넘어온 handler를 ControllerV4 형태로 캐스팅한다.
        // supports()를 통해 ControllerV4 인스턴스인 handler만 매개변수로 전달되므로, 캐스팅이 가능하다.
        ControllerV4 controller = (ControllerV4) handler;

        // createParamMap(): 모든 파라미터 이름을 대상으로 paramMap을 생성한다.
        Map<String, String> paramMap = createParamMap(request);

        // V4 구조: paramMap + Model이 함께 파라미터로 넘어간다.
        HashMap<String, Object> model = new HashMap<>();

        // 매핑되는 Controller 인스턴스가 존재한다면, 해당 Controller의 process를 실행한다.
        // process()의 매개변수로 paramMap, Model을 넘겨준다.
        // process() 실행 후 반환된 뷰 이름을 viewName에 저장한다. (Model에 데이터가 담겨서 돌아온다.)
        String viewName = controller.process(paramMap, model);

        // viewName은 String 형태로, handle()의 반환 형태인 ModelView와 일치하지 않는다.
        // 이를 해결하기 위해 어댑터를 활용한다.

        // 뷰 이름을 초기화 하며 ModelView 객체를 생성한다.
        ModelView mv = new ModelView(viewName);

        // model을 매개변수로 넘겨 Model을 세팅한다.
        mv.setModel(model);

        return mv;
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