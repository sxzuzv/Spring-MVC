<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Member Info</title>
</head>
<body>
성공!
<ul>
<%-- request.getAttribute()의 경우, Object 타입으로 값이 반환되므로 형변환이 필요하다. --%>
<%--    <li>ID: <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>이름: <%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>나이: <%=((Member)request.getAttribute("member")).getAge()%></li>--%>

<%-- JSP 표현식을 사용하여 코드 간략화: 프로퍼티 접근법 사용 --%>
    <li>아이디: ${member.id}</li>
    <li>이름: ${member.username}</li>
    <li>나이: ${member.age}</li>
</ul>
<a href="/index.html">메인으로</a>
</body>
</html>
