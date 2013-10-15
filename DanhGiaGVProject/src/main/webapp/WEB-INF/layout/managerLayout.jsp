<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns:tiles="http://tiles.apache.org/tags-tiles">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/script/script.js"
	type="text/javascript"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/style.css"
	type="text/css" />
<tiles:insertAttribute name="addlib" ignore="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:directive.page contentType="text/html; charset=UTF-8" />
</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/bgcongcuphai.png);">
	<tiles:insertAttribute name="header" ignore="true" />
	<header>
		<div class="inside header">
			<a id="logo" href="#"><span class="congthongtin">PM.ĐÁNH
					GIÁ GIẢNG VIÊN</span><br /><tiles:insertAttribute name="pageName" ignore="true" /><br /> <span>ĐẠI HỌC SƯ
					PHẠM KỸ THUẬT TP. HỒ CHÍ MINH</span> <img
				src="${pageContext.request.contextPath}/resources/images/logo_1.png"
				border="0px" /></a>
		</div>
	</header>
	<div class="main-content inside">
		<maincontent> <!-- TemplateBeginEditable name="maincontent" -->
		<tiles:insertAttribute name="menuright"/>
		<tiles:insertAttribute name="body" /> 
		<!-- TemplateEndEditable --> 
		</maincontent>
	</div>
	<tiles:insertAttribute name="footer" ignore="true" />
</body>
</html>