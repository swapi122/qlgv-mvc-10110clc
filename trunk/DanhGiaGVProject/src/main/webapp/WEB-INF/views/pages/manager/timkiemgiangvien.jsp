<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/manager"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label class="focus">
			Trang Chủ </label>>Tìm kiếm giảng viên
		<hr class="line-header-padding" />
	</div>
	<div id="public">
		<span style="text-decoration: underline;">Tìm kiếm :</span>
		<form action="${pageContext.request.contextPath}/manager/timkiemGV" method="post" style="width: 400px; margin: auto" >
		<table>
			<tr>
				<td>ID :</td><td><input type="text" name="idgv"/></td>
			</tr>
			<tr>
				<td>Họ Tên :</td><td><input size="40" type="text" name="hoten"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="button" type="submit" value="Tìm Kiếm"/></td>
			</tr>
		</table>
		</form>
	</div>

</div>