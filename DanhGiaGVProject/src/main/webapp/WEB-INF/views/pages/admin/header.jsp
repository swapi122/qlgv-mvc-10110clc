<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/adminstyle.css" />

 
<!-- jQuery and jQuery UI --> 
<spring:url value="/resources/styles/custom-theme/jquery-ui-1.10.3.custom.css" 
var="jquery_ui_theme_css" /> 
<link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_theme_css}" />
<script src="${pageContext.request.contextPath}/resources/jqgrid/jquery.js" type="text/javascript" ></script> 
<script src="${pageContext.request.contextPath}/resources/jqgrid/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<script type="text/javascript">
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
</script>


<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/resources/jqgrid/ui.jqgrid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jqgrid/jquery.jqGrid.js"></script>
<script src="${pageContext.request.contextPath}/resources/jqgrid/plugins/grid.addons.js" type="text/javascript"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
