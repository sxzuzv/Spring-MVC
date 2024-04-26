package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

// Model 객체를 파라미터로 전달하며, 뷰의 이름만 반환한다.
public interface ControllerV4 {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
