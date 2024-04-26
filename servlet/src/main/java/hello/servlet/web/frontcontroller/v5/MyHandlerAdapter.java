package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {
    // handler(controller)가 매개변수로 넘어왔을 때, 이를 처리할 수 있는 어댑터인지를 판단한다.
    boolean supports(Object handler);   // true or false

    ModelView handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
