<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Java 코드 작성: 전체 회원 리스트를 받아오는 비즈니스 로직 --%>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();

  List<Member> members = memberRepository.findAll();
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
  <a href="/index.html">메인으로</a>
  <table>
    <thead>
      <th>ID</th>
      <th>이름</th>
      <th>나이</th>
    </thead>
    <tbody>
      <%-- Java 코드를 사용하여 전체 회원 정보 리스트를 출력한다. --%>
      <%
        for (Member member : members) {
          out.write("    <tr>");
          out.write("        <td>"+member.getId()+"</td>");
          out.write("        <td>"+member.getUsername()+"</td>");
          out.write("        <td>"+member.getAge()+"</td>");
          out.write("    </tr>");
        }
      %>
    </tbody>
  </table>
</body>
</html>