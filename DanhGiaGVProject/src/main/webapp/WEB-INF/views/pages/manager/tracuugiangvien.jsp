<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/manager"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label class="focus">
			Trang Chủ </label>>Tra cứu giảng viên
		<hr class="line-header-padding" />
	</div>
	<div id="private">
		<u>Danh sách giảng viên :</u>
		<c:forEach items="${gvs}" var="gv">
			<a href="${pageContext.request.contextPath}/manager/tracuugv/${gv.id}">
				<div id="public" style="width: 570px">
					<div style="width: 570px ; color: white; background-color: #3B5998;">${gv.id}</div>
					<div style="width: 570px ; ">${gv.hoten}</div>
				</div>
			</a>
		</c:forEach>
	</div>
	
</div>