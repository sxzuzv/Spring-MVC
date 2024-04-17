package hello.servlet.web.serlvet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTML Form에서 입력된 회원 정보를 저장한다.
        System.out.println("MemberSaveServlet.service");

        // request.getParameter()를 활용해 입력된 데이터를 받아온다.
        String username = request.getParameter("username");

        // request.getParameter()의 반환 형태는 String, 숫자 타입으로 변환한다.
        int age = Integer.parseInt(request.getParameter("age"));

        // 입력받은 데이터를 사용해 Member 객체를 생성한다.
        Member member = new Member(username, age);
        memberRepository.save(member);  // 저장소에 Member 객체를 저장한다.

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        // 응답 결과(성공)를 HTML로 내려준다.
        writer.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
