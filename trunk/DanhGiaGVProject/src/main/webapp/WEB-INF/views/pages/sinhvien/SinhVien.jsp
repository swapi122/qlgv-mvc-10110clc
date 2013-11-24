<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="bdgChoose"
	class="com.nvh.applicationscope.BangDanhGiaChoose" scope="application" />
<%
	bdgChoose.setId(19);
%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/sinhvien"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus"
			href="${pageContext.request.contextPath}/sinhvien/">Thời khóa biểu</a>
		<hr class="line-header-padding" />
	</div>
	<c:forEach items="${tkblist}" var="tkb">
		<a
			href="${pageContext.request.contextPath}/sinhvien/danhgia/${tkb.id}">
			<div id="private">
				<div id="row">${tkb.monhoc.tenMH}</div>
				<div id="row">${tkb.gv.hoten}</div>
			</div>
		</a>
	</c:forEach>
</div>