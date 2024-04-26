package hello.servlet.web.frontcontroller.v4.controller;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // ModelView 객체가 필요하지 않으며, 뷰 이름만 반환해주면 된다.
        return "new-form";
    }
}