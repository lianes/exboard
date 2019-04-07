<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<h2>회원 가입하기</h2>
<h4>다음의 정보를 입력해 주세요.</h4>
<form method="post" action="registerok">
아이디 <input type="text" name="userId"><br>
이름 <input type="text" name="userName"><br>
이메일 <input type="text" name="userEmail"><br>
비밀번호 <input type="text" name="userPassword"><br>
<br>
<input type="submit" value="등록하기">
</form>
</body>
</html>