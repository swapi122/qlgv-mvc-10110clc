<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
function comeback(){
	window.location.assign("${pageContext.request.contextPath}/admin/qluserlist");
}
</script>
<div class="content">
	<div id="public">
		<div id="row"><b>ID :</b> ${info.id}</div>
		<div id="row"><b>Họ Tên</b> : ${info.hoten}</div>
		<div id="row"><b>Nơi Sinh</b> : ${info.noisinh}</div>
		<div id="row"><b>Ngày Sinh</b> : ${info.ngaysinh}</div>
		<div id="row"><b>Loại account</b> : ${info.typeaccount}</div>
		<div id="row" align="right"><input id="button" type="button" value="Trở về" onclick="comeback()"></div>
	</div>
</div>