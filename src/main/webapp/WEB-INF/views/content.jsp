<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.postPadding { padding: 10px; }
	.postRow { border-top: 1px solid gray; }
	.postTitle { font-size: 24px; }
	.postUsername { font-size: 16px; font-weight: bold; }
	.postDate { font-size: 12px; color: LightSlateGray; }
	.postReadCount { font-size: 12px; color: LightSlateGray; }
	.postBody { white-space: pre; font-size: 16px; padding-top: 20px; padding-bottom: 20px; }
	.buttonStyle {
		color: black;
		text-decoration: none;	
		padding: 15px 25px;
		display: inline-block;
		border: 1px solid rgba(0,0,0,0.21);
		border-bottom-color: rgba(0,0,0,0.34);
		text-shadow:0 1px 0 rgba(0,0,0,0.15);
	}
	.buttonStyle:active {
		top: 1px;
		border-color: rgba(0,0,0,0.34) rgba(0,0,0,0.21) rgba(0,0,0,0.21);
		position: relative;
	}
</style>
<title>글 보기</title>
</head>
<body>
<div class="postPadding"></div>
<div class="postTitle">📄 ${post.title}</div>
<div class="postPadding"></div>
<div class="postRow"></div>
<div class="postUsername">🙂 ${user.name}</div>
<div class="postDate">작성 시간: ${post.createTime}</div>
<div class="postDate">수정 시간: ${post.modifyTime}</div>
<div class="postReadCount">조회수: ${post.readCount}</div>
<div class="postRow"></div>
<div class="postPadding"></div>
<div class="postBody">${post.content}</div>
<div class="postPadding"></div>
<div class="postPadding"></div>
<div class="postPadding"></div>

<div align="center">
<c:if test="${loginUserId ne null and loginUserIndex == post.userIndex}">
<a class="buttonStyle" href="../modify">글 수정</a> &nbsp; <a class="buttonStyle" href="javascript:deletePost();">글 삭제</a>
</c:if>
</div>

<!-- 댓글 -->
<%@ include file="./view_comments.jsp" %>

<div align="center">
<a class="buttonStyle" href="../posts">글 목록보기</a>
</div>
<div style="padding: 20px"></div>
</body>

<script>
function deletePost() {
	if(confirm("정말 삭제하시겠습니까?")) {
		location.href = "../delete";
	}
}
</script>
</html>