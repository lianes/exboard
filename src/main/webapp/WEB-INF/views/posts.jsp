<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.table { display: table; width: 100%; }
	.tableBody { display: table; width: 100%; }
	.tableRow { display: table-row; }
	.tableCell { border: 1px solid #999; display: table-cell; }
	.index { display: table-cell; color: gray; }
	.title { display: table-cell; color: blue; }
	.readcount { display: table-cell; color: orange; }
	.username { display: table-cell; color: indigo; }
	.buttonStyle { color: black; padding: 5px; text-decoration: none; display: inline-block; border: 1px solid rgba(0,0,0,0.21); border-bottom-color: rgba(0,0,0,0.34); text-shadow:0 1px 0 rgba(0,0,0,0.15); }
	.buttonStyle:active { top: 1px;	border-color: rgba(0,0,0,0.34) rgba(0,0,0,0.21) rgba(0,0,0,0.21); position: relative; }
	.buttonStyle.logout { color: white; background-color: silver; }
</style>
<title>게시판</title>
</head>

<body>
<h2>게시판</h2>
전체 게시물 수: ${count}

<div style="padding: 10px; padding-top: 1px;"></div>

<c:if test="${loginUserId eq null or empty loginUserId}">
	<a class="buttonStyle" href="./register">가입하기</a> &nbsp; <a class="buttonStyle" href="./login">로그인하기</a>
</c:if>
<c:if test="${loginUserId ne null}">
	${loginUserId}님 접속 중입니다 &nbsp; <a class="buttonStyle logout" href="./logout">로그아웃</a>
</c:if>

<div style="padding: 10px"></div>

<div class="table" style="width: 800px;">
	<div class="tableBody">
		<div class="tableRow">
			<div class="tableCell" style="width: 100px;">글 번호</div>
			<div class="tableCell">제목</div>
			<div class="tableCell" style="width: 80px;">이름</div>
			<div class="tableCell" style="width: 100px;">조회수</div>
			<div class="tableCell" style="width: 180px;">작성시간</div>
		</div>
		<c:forEach items="${posts}" var="post">
		<div class="tableRow">
			<div class="tableCell">${post.index}</div>
			<div class="tableCell"><a href="./content/${post.index}">${post.title}</a></div>
			<div class="tableCell">${post.name}</div>
			<div class="tableCell">${post.readCount}</div>
			<div class="tableCell"><fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd hh:mm" /></div>
		</div>
		</c:forEach>
	</div>
</div>

<div style="padding: 10px"></div>

<div class="page">
	페이지&nbsp;
	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="posts?start=${pageIndex}">${status.index + 1}</a>&nbsp; &nbsp;
	</c:forEach>
</div>

<div style="padding: 10px"></div>

<c:if test="${loginUserId ne null}">
	<a class="buttonStyle" href="./write">글 쓰기</a>
</c:if>

</body>
</html>