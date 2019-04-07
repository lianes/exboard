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
<h2>글 수정</h2>
<form method="post" action="modifyok">
<table style="border: 1px; solid #aaaaaa">
	<thead>
		<tr>
			<th style="text-align: left;">글 수정하기</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input type="text" name="title" value="${post.title}" style="width: 500px;"><br></td>
		</tr>
		<tr>
			<td><textarea name="content" rows="6" cols="60" style="width: 500px;">${post.content}</textarea></td>
		</tr>
	</tbody>
</table>
<input type="submit" value="수정 완료">
</form>
<br>
<a href="javascript:history.back();">뒤로 가기</a>
</body>
</html>