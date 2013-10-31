<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script
	src="${pageContext.request.contextPath}/resources/script/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/adminstyle.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />


<!-- jQuery and jQuery UI --> 
<spring:url value="/resources/scripts/jquery-1.7.1.js" var="jquery_url" /> 
<spring:url value="/resources/scripts/jquery-ui-1.8.16.custom.min.js" 
var="jquery_ui_url" /> 
<spring:url value="/resources/styles/custom-theme/jquery-ui-1.8.16.custom.css" 
var="jquery_ui_theme_css" /> 
<link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_theme_css}" /> 
<script src="${jquery_url}" type="text/javascript"><jsp:text/></script> 
<script src="${jquery_ui_url}" type="text/javascript"><jsp:text/></script> 

<spring:url value="/resources/jqgrid/css/ui.jqgrid.css" var="jqgrid_css" />
<spring:url value="/resources/jqgrid/js/i18n/grid.locale-en.js"
	var="jqgrid_locale_url" />
<spring:url value="/resources/jqgrid/js/jquery.jqGrid.min.js"
	var="jqgrid_url" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${jqgrid_css}" />
<script type="text/javascript" src="${jqgrid_locale_url}"></script>
<script type="text/javascript" src="${jqgrid_url}"></script>
