<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
<h2>글 작성</h2>
<form method="post" action="writeok">
<table style="border: 1px; solid #aaaaaa">
	<thead>
		<tr>
			<th style="text-align: left;">글 작성하기</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input type="text" name="title" placeholder="제목" style="width: 500px;"><br></td>
		</tr>
		<tr>
			<td><textarea name="content" rows="6" cols="60" placeholder="내용" style="width: 500px;"></textarea></td>
		</tr>
	</tbody>
</table>
<input type="submit" value="작성 완료">
</form>
<br>
<a href="javascript:history.back();">뒤로 가기</a>
</body>
</html>