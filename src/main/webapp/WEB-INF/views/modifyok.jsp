<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
<h2>글 수정 완료</h2>
<h4>글을 수정했습니다.</h4>
<br>
잠시 후 이전 페이지로 돌아갑니다...
</body>
<script type='text/javascript'>
setTimeout(() => {
	window.location.href="./posts";
}, 3000);
</script>
</html>