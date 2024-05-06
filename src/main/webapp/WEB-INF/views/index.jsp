<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	이 페이지는 메인입니다.
	<c:if test="${empty loginUser}">
		<form method="post" action="${root}/user/login">
		<input type="text" name="id"/>
		<input type="text" name="password"/>
		<button type="submit">로그인</button>
	</form>
	</c:if>
	<a href="${root}/chat/list">채팅방 목록으로 이동하기</a>
</body>
</html>