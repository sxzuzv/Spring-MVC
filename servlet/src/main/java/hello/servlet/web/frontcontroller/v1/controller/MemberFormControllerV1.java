package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// ControllerV1 Interface를 구현하는 Controller: 회원 등록을 위한 MemberFormControllerV1
public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // request.getRequestDispatcher(): Controller -> View 경로 이동 시 사용한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // Servlet에서 JSP를 호출한다.
        dispatcher.forward(request, response);
    }
}
