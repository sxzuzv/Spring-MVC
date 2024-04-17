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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 현재까지 저장된 모든 회원 정보 리스트를 확인한다.
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("    <meta charset=\"UTF-8\">");
        writer.write("    <title>Title</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<a href=\"/index.html\">메인</a>");
        writer.write("<table>");
        writer.write("    <thead>");
        writer.write("    <th>id</th>");
        writer.write("    <th>username</th>");
        writer.write("    <th>age</th>");
        writer.write("    </thead>");
        writer.write("    <tbody>");

        // 반복문을 실행하여 List에 담긴 회원 정보를 출력한다.
        for (Member member : members) {
            writer.write("    <tr>");
            writer.write("        <td>"+member.getId()+"</td>");
            writer.write("        <td>"+member.getUsername()+"</td>");
            writer.write("        <td>"+member.getAge()+"</td>");
            writer.write("    </tr>");
        }

        writer.write("    </tbody>");
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
