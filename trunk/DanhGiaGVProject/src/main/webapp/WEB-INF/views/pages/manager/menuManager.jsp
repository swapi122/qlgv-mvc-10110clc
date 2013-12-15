<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="right-bar">
	<p>Manager</p>
	<hr />
	<div id="ketqua">
		<a href="${pageContext.request.contextPath}/manager/tracuugv">
			<div class="row">Tra cứu giảng viên</div>
		</a> <a href="${pageContext.request.contextPath}/manager/timkiemGV">
			<div class="row">Tìm giảng viên</div>
		</a>
	</div>
	<br />
</div>