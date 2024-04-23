package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    // v1 -> v2: process() 수행 후, MyView 객체를 반환한다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
