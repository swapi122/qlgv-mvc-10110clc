<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>
input.text,select {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 10px;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
$(function() {
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 400,
		width : 300,
		modal : true,
		buttons : {
			"Update" : function() {
				formData = $('#inputForm').serialize();
				$.ajax({
					url : $("#inputForm").attr("action"),
					type : 'POST',
					data : formData,
					success : function() {
						alert("Update thành công");
						location.reload();
					}
				});
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});

	
});
function openDialog(id) {
	$("#inputForm").attr("action",
			"${pageContext.request.contextPath}/admin/qluserlist?update");
	$("#dialog-form").dialog("open");
};
function comeback(){
	window.location.assign("${pageContext.request.contextPath}/admin/qluserlist");
}
</script>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus" href="${pageContext.request.contextPath}/admin/qluserlist">Quản lý Users</a> &gt; <a class="focus"
			href="${pageContext.request.contextPath}/admin/user/${info.id}">Thông tin User</a>
		<hr class="line-header-padding" />
	</div>
	
	<div id="dialog-form" title="Cập nhật User">
			<form id="inputForm" method="post">
				<fieldset>
					<label for="id">ID :</label> 
					<input type="text" name="id" id="id" class="text ui-widget-content ui-corner-all" readonly="readonly" value="${info.id}" /> 
					<label for="hoten">Họ Tên :</label> 
					<input type="text" name="hoten" id="hoten" value="${info.hoten}"	class="text ui-widget-content ui-corner-all" />
					<label for="noisinh">Nơi Sinh :</label> 
					<input type="text" name="noisinh" id="noisinh" value="${info.noisinh}"	class="text ui-widget-content ui-corner-all" />
					<label for="ngaysinh">Ngày Sinh :</label> 
					<input type="text" name="ngaysinh" id="ngaysinh" value="${info.ngaysinh}"	class="text ui-widget-content ui-corner-all" />
					<label for="loaiaccount">Loai Account :</label> 
					<select name="loaiaccount" id="loaiaccount" class="text ui-widget-content ui-corner-all" >
						<option value="0">Sinh Viên</option>
						<option value="1">Giảng Viên</option>
						<option value="2">Manager</option>
						<option value="3">Admin</option>
					</select>
				</fieldset>
			</form>
		</div>
	
	<div id="public" style="position: relative; height: 200px">
			<img style="border : 1px solid black ;position: absolute; top : 10px ; right: 30px ;width: 140px ; height : 160px ;" alt="anh sinh vien" src="http://online.hcmute.edu.vn/HinhSV/${info.id}.jpg">
		<div style="width: 400px; position: absolute; ; top : 10px ; left: 10px">
			<div id="row" style="width: 400px;"><b>ID :</b> ${info.id}</div>
			<div id="row" style="width: 400px;"><b>Họ Tên</b> : ${info.hoten}</div>
			<div id="row" style="width: 400px;"><b>Nơi Sinh</b> : ${info.noisinh}</div>
			<div id="row" style="width: 400px;"><b>Ngày Sinh</b> : ${info.ngaysinh}</div>
			<div id="row" style="width: 400px;"><b>Loại account</b> 
			<c:choose>
				<c:when test="${info.typeaccount == 0}"> : Sinh Viên</c:when>
				<c:when test="${info.typeaccount == 1}"> : Giảng Viên</c:when>
				<c:when test="${info.typeaccount == 2}"> : Manager</c:when>
				<c:when test="${info.typeaccount == 3}"> : Admin</c:when> 
			</c:choose>
			</div>
		</div>
		<div id="row" align="right" style="position: absolute; bottom: 10;">
			<input id="button" type="button" value="Cập nhật" onclick="openDialog(${info.id})"/>
			<input id="button" type="button" value="Trở về" onclick="comeback()">
		</div>
	</div>
</div>