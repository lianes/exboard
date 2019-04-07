<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<h4>로그인 정보를 입력해 주세요.</h4>
<form method="post" action="loginok">
아이디 <input type="text" name="userId"><br>
비밀번호 <input type="text" name="userPassword"><br>
<br>
<input type="submit" value="로그인">
</form>

<c:if test="${isLoginFail eq 'true'}">
<p>
	<span style="color:red">
	아이디와 비밀번호를 확인해 주세요.
	</span>
</p>
</c:if>

</body>
</html>