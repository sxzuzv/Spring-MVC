<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Java 코드 작성: 회원 저장 비즈니스 로직 --%>
<%
  // request, response 사용 가능 (JSP도 자동으로 Servlet으로 변환된다.)
  MemberRepository memberRepository = MemberRepository.getInstance();

  System.out.println("MemberSaveServlet.service");

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공!
<ul>
  <li>아이디: <%=member.getId()%></li>
  <li>이름: <%=member.getUsername()%></li>
  <li>나이: <%=member.getAge()%></li>
</ul>
<a href="/index.html">메인으로</a>
</body>
</html>
