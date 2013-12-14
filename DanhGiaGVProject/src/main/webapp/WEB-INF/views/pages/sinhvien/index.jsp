<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/sinhvien"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus" href="${pageContext.request.contextPath}/sinhvien/">Thông báo</a>
		<hr class="line-header-padding" />
	</div>
	<c:forEach items="${tblist}" var="tb">
		<div id="public" style="width: 600px">
			<div id="row">
				<u>Thông báo :</u> ${tb.id}
			</div>
			<div id="row">
				<b>Nội dung :</b> ${tb.tenthongbao}
			</div>
			<div id="row">
				<b>Ngày đăng thông báo :</b> ${tb.ngaytao}
			</div>
		</div>
	</c:forEach>
</div>