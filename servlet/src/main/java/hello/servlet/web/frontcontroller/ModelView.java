package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName; // view의 논리 이름
    private Map<String, Object> model = new HashMap<>();

    // view의 논리 이름을 매개변수로 받는 생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    // getter, setter
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}