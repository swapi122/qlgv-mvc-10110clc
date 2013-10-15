<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns:tiles="http://tiles.apache.org/tags-tiles">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/script/script.js"
	type="text/javascript"></script>
<title><tiles:insertAttribute name="title" ignore="true"/></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/style.css"
	type="text/css" />
</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/bgcongcuphai.png);">
	<tiles:insertAttribute name="header" ignore="true" />
	<div class="main-content inside">
		<maincontent> 
		<!-- TemplateBeginEditable name="maincontent" -->
		<tiles:insertAttribute name="body"/>
		<!-- TemplateEndEditable --> 
		</maincontent>
	</div>
	<tiles:insertAttribute name="footer" ignore="true"/>
</body>
</html>