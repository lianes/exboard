<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.table { display: table; }
	.tableBody { display: table; }
	.tableRow { display: table-row; }
	.tableCell { border: 1px solid #999; display: table-cell; }
	.index { display: table-cell; color: gray; }
	.readcount { display: table-cell; color: orange; }
	.username { display: table-cell; color: indigo; }
	.header { background-color: gainsboro; }
	
	.commentRow { border-top: 1px dashed SlateGray; padding: 10px; }
	.commentUser { font-weight: bold; }
	.commentTime { font-size: 12px; color: LightSlateGray; }
	.commentContent { white-space: pre; padding: 10px; }
</style>
<title>댓글</title>
</head>
<body>

<div>&nbsp;</div>
<div><h4>댓글 목록</h4></div>
<div class="comment">

	    <div class="commentBody">
	      <!-- template -->
	    </div>

		<div class="commentRow writeComment">
			<form id="write" method="post" action="/board/comments">
				<table class="commentTable">
					<tr>
						<td><textarea id="commentContent" name="commentContent" rows="4" cols="60" placeholder="댓글을 입력하세요. 댓글은 로그인 후 작성할 수 있습니다." style="width: 500px;"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" value="댓글 등록" onclick="checkWriteComment();"></td>
					</tr>
				</table>
			</form>
		</div>
		
		<div>
		</div>

</div>
<br>
<!-- html template -->
<script id="commentTemplate" type="text/template">
<div class="commentRow" id="commentIndex{commentIndex}">
  <div class="commentUser">🙂 {name}</div>
  <div class="commentContent">{content}</div>
  <div class="commentTime">작성시간: {createTime}</div>
  <div class="commentTime">수정시간: {modifyTime}</div>
  <div>
    <form method="post">
      <input type="hidden" id="idDeleteComment" name="method" value="delete" />
      <input type="button" value="삭제" onclick="deleteComment({commentIndex}, {userIndex});" />
      <input type="button" value="수정" onclick="showModifyForm({commentIndex}, {userIndex});" />
    </form>
  </div>
</div>
</script>

<script id="modifyCommentTemplate" type="text/template">
<div class="commentRow modifyCommentBody">
  <form id="modify" method="post">
    <table class="commentTable">
      <tr>
        <td><textarea id="idModifyComment" name="nameModifyComment" rows="4" cols="60" placeholder="수정할 내용을 입력하세요." style="width: 500px;"></textarea></td>
      </tr>
      <tr>
        <td>
			<input type="button" value="수정 완료" onclick="modifyComment({commentIndex});">
			<input type="button" value="취소" onclick="removeModifyForm();">
		</td>
      </tr>
    </table>
  </form>
</div>
</script>

<script>
window.onload = function() {
	getComments();
}

function getCommentHeader() {
  var commentHeader = '<div class="tableRow header">\
      <div class="tableCell">댓글 번호</div>\
      <div class="tableCell">내용</div>\
      <div class="tableCell">이름</div>\
      <div class="tableCell">작성시간</div>\
      <div class="tableCell">수정시간</div>\
      <div class="tableCell">삭제</div>\
    </div>\
  ';

  return commentHeader;
}

function getComments() {
	console.log("******* getComments()... ");
	var xhr = new XMLHttpRequest();
	var text;
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("******* RESPONSE TEXT...\n" + this.responseText);
			text = this.responseText;
			var jsonObj = JSON.parse(text);
			var templateHTML = document.querySelector("#commentTemplate").innerHTML;
			var resultHTML = "";

			if(jsonObj.comments.length != 0) {
		        var length = jsonObj.comments.length;
		     	// 댓글 index 오름차순
		        for (var i = length-1; i >= 0; i--) {
        		  resultHTML += templateHTML.replace("{commentIndex}", jsonObj.comments[i].index)
					.replace("{name}", jsonObj.comments[i].name)
					.replace("{content}", jsonObj.comments[i].content)
					.replace("{createTime}", getHumanTime(jsonObj.comments[i].createTime))
					.replace("{modifyTime}", getHumanTime(jsonObj.comments[i].modifyTime))
					.replace("{commentIndex}", jsonObj.comments[i].index)
					.replace("{userIndex}", jsonObj.comments[i].userIndex)
        		  	.replace("{commentIndex}", jsonObj.comments[i].index)
					.replace("{userIndex}", jsonObj.comments[i].userIndex);
				}
			}
			else {
				resultHTML += "<div>";
				resultHTML += "<div>댓글이 없습니다.</div>";
				resultHTML += "</div>";
			}
			document.querySelector(".commentBody").innerHTML = resultHTML;
		}
	};

	xhr.open("GET", "/board/comments/${post.index}");
	xhr.send();
}

function getHumanTime(epoch) {
	if(epoch == null)
		return ' ';

	var date = new Date(epoch);
	var dateInfo = date.toLocaleDateString(date) + " " + date.toLocaleTimeString(date);

	return dateInfo;
}

var loginUserIndex = null;

function checkWriteComment() {
	if('${loginUserIndex}')
		loginUserIndex = '${loginUserIndex}';

	if(loginUserIndex == null || loginUserIndex == '') {
		console.log("전송취소: 로그인 안 됨");
		alert("댓글은 로그인 후 작성할 수 있습니다.");
	} else {
		writeComment();
		console.log("******* 댓글 전송 완료!");
	}
}

function writeComment() {
	var writeCommentIndex = 0;
	var writeContent = document.getElementById('commentContent').value;
	var writeTime = Math.round(new Date()/1000);
	var writeUserIndex = loginUserIndex;
	var writePostIndex = ${post.index};
	var writeData = JSON.stringify({ index: 0, content: writeContent, createTime: writeTime, modifyTime: writeTime, userIndex: writeUserIndex, postIndex: writePostIndex });

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			getComments();
			document.getElementById('commentContent').value="";
			document.getElementById('commentContent').placeholder = "댓글을 입력하세요. 댓글은 로그인 후 작성할 수 있습니다.";
		}
	};
	xhr.open("POST", "/board/comments", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(writeData);
}

function deleteComment(commentIndex, userIndex) {
	if(isLogin() == false) {
		alert("로그인을 해야 합니다.");
		return;
	}

	if('${loginUserIndex}')
		loginUserIndex = '${loginUserIndex}';

	console.log("deleteComment ******* commentIndex: " + commentIndex + " / userIndex: " + userIndex + " / loginUserIndex: " + loginUserIndex);

	if(isCommentWriter(userIndex, loginUserIndex) == false) {
		alert("본인의 댓글만 삭제할 수 있습니다.");
		return;
	}

	var isDelete = confirm("정말 삭제할까요?");
	if(isDelete == false)
		return;
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("******* deleteComment... index: " + commentIndex);
			getComments();
		}
	};
	xhr.open("DELETE", "/board/comments/delete/" + commentIndex, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();
}

function isLogin() {
	var loginSuccess = false;

	if('${loginUserIndex}')
		loginUserIndex = '${loginUserIndex}';

	if(loginUserIndex == null || loginUserIndex == '') {
		console.log("로그인 안 됨");
		loginSuccess = false;
	} else {
		console.log("로그인 체크: " + loginUserIndex);
		loginSuccess = true;
	}

	return loginSuccess;
}

function isCommentWriter(userIndex, loginUserIndex) {
	if(userIndex == loginUserIndex)
		return true;
	else
		return false;
}

function showModifyForm(commentIndex, userIndex) {
	var modifyFormCount = document.getElementsByClassName("modifyCommentBody").length;
	if(modifyFormCount != 0)
		return;
	
	if(isLogin() == false) {
		alert("로그인을 해야 합니다.");
		return;
	}

	if('${loginUserIndex}')
		loginUserIndex = '${loginUserIndex}';

	console.log("modifyComment ******* commentIndex: " + commentIndex + " / userIndex: " + userIndex + " / loginUserIndex: " + loginUserIndex);

	if(isCommentWriter(userIndex, loginUserIndex) == false) {
		alert("본인의 댓글만 수정할 수 있습니다.");
		return;
	}

	var divCommentIndex = "";
	divCommentIndex += "#commentIndex" + commentIndex;
	var commentRowBody = document.querySelector(divCommentIndex);
	var insertHTML = getModifyHTML(commentIndex);
	var resultHTML = "";
	resultHTML += commentRowBody.innerHTML;
	resultHTML += insertHTML;
	document.querySelector(divCommentIndex).innerHTML = resultHTML;
}

function getModifyHTML(commentIndex) {
	var modifyHTML = "";
	var templateHTML = document.querySelector("#modifyCommentTemplate").innerHTML;
	modifyHTML += templateHTML.replace("{commentIndex}", commentIndex);
	
	return modifyHTML;
}

function modifyComment(commentIndex) {
	console.log("******* modify comment ******* index: " + commentIndex);
	
	var modifyCommentIndex = commentIndex;
	var modifyContent = document.getElementById("idModifyComment").value;
	var modifyTime = Math.round(new Date()/1000);
	var modifyUserIndex = loginUserIndex;
	var modifyPostIndex = ${post.index};
	var modifyData = JSON.stringify({ index: modifyCommentIndex, content: modifyContent, createTime: modifyTime, modifyTime: modifyTime, userIndex: modifyUserIndex, postIndex: modifyPostIndex });
	
	console.log("modify json\n" + modifyData);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			getComments();
		}
	};
	xhr.open("PUT", "/board/comments", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(modifyData);
}

function removeModifyForm() {
	var tempHTML = document.querySelector(".modifyCommentBody");
	tempHTML.parentNode.removeChild(tempHTML);
}

</script>

</body>
</html>
