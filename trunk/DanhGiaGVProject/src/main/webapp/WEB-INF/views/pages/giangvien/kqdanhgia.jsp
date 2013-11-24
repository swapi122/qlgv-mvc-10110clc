<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="content">
	<a href="${pageContext.request.contextPath}/gvien/"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a class="focus"
					href="${pageContext.request.contextPath}/gvien/kqdanhgia/">Môn giảng dạy</a>
	<hr class="line-header-padding" />

	<c:choose>
		<c:when test="${not empty message}">
			<div id="private"><div id="row">${message}</div></div>
		</c:when>
		<c:when test="${not empty tkbs}">
			<c:forEach items="${tkbs}" var="tkb">
				<a
					href="${pageContext.request.contextPath}/gvien/kqdanhgia/${tkb.ID}"><div
						id="public">${tkb.tenMH}</div></a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="public">
				<div id="row">Hiện tại giảng viên không có môn giảng dạy.</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>