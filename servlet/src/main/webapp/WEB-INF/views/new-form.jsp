<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Member Form</title>
</head>
<body>
<%-- 상대 경로 사용: [현재 URL이 속한 계층 경로 + /save] --%>
<%-- '/save'로 지정할 시, localhost:8080/save로 인식되지만 '/' 없이 지정할 경우 상대 경로로 인식된다. --%>
    <form action="save" method="post">
        username: <input type="text" name="username">
        age: <input type="text" name="age">
        <button type="submit">등록</button>
    </form>
</body>
</html>
