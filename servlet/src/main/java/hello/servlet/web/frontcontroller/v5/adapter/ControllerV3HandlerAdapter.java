package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ControllerV3 방식의 호환을 돕는 HandlerAdapter를 구현한다.
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // 매개변수로 넘어온 handler가 ControllerV3의 인스턴스인지를 판단한다. => true or false
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        // 매개변수로 넘어온 handler를 ControllerV3 형태로 캐스팅한다.
        // supports()를 통해 ControllerV3 인스턴스인 handler만 매개변수로 전달되므로, 캐스팅이 가능하다.
        ControllerV3 controller = (ControllerV3) handler;

        // ControllerV3 방식의 경우, paramMap이 필요하다.
        Map<String, String> paramMap = createParamMap(request);

        // paramMap을 매개변수로 넘겨서 process()를 실행한다.
        // 반환된 ModelView를 변수 mv에 저장한다.
        ModelView mv = controller.process(paramMap);

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