<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> 
			<a href="${pageContext.request.contextPath}/admin?qldg">Danh Sách Bảng Đánh Giá</a> &gt;
			<a class="focus" href="${pageContext.request.contextPath}/admin?form">Thêm Bảng Đánh Giá</a>
		<hr class="line-header-padding" />
	</div>
	<form action="${pageContext.request.contextPath}/admin?form" method="post">
		<table style="margin: 30px auto auto auto ; width: 500px">
			<tr>
				<td colspan="2" style="text-align: center;background-color: #DFE4EE">THÊM BẢNG ĐÁNH GIÁ
					GIẢNG VIÊN</td>
			</tr>
			<tr>
				<td>Nhập Tên Bảng :</td>
				<td><input id="tenbang" type="text" size="49"
					name="tenbang" /></td>
			</tr>
			<tr>
				<td align="right"><input id="button" type="submit" value="Lưu" /></td>
				<td><input type="reset" id="button" value="Reset" /></td>
			</tr>
		</table>
	</form>
</div>