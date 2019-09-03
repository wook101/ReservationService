<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<c:forEach items="${Lists }" var="reserve" varStatus="status">
	<c:if test="${status.index % 2 eq 0 }">
		<li id="${reserve.id}">
			<img src="img/main/${reserve.imgPath }">
			<h3>${reserve.description }</h3>
			<h5>${reserve.place_name }</h5>
			<p>${reserve.content }</p>
		</li>
	</c:if>
</c:forEach>	
</body>
</html>
------
<html>
<body>
<c:forEach items="${Lists }" var="reserve" varStatus="status">
	<c:if test="${status.index%2 eq 1 }">
		<li id="${reserve.id}">
			<img src="img/main/${reserve.imgPath }">
			<h3>${reserve.description }</h3>
			<h5>${reserve.place_name }</h5>
			<p>${reserve.content }</p>
		</li>
	</c:if>
</c:forEach>	
</body>
</html>
------
${listCount}
