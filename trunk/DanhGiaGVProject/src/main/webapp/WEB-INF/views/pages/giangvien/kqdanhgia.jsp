<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="content">
	<c:choose>
		<c:when test="${not empty tkbs}">
			<c:forEach items="${tkbs}" var="tkb">
				<a href="${pageContext.request.contextPath}/gvien/kqdanhgia/${tkb.ID}"><div id="private">${tkb.tenMH}</div></a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="private">
				<div id="row">
					Hiện tại giảng viên không có môn giảng dạy.
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>