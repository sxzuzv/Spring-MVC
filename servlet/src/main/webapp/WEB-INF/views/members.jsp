<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Member List</title>
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
  <%-- JSP가 제공하는 태그(jstl)를 사용해 전체 회원 리스트를 출력한다. --%>
  <c:forEach var="item" items="${members}">
      <tr>
        <td>${item.id}</td>
        <td>${item.username}</td>
        <td>${item.age}</td>
      </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
