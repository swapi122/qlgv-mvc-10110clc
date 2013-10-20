<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
function setdanhgia(URL) {
	$.ajax({
		url : URL + $("#selectloai").val(),
		type : 'POST',
		success : function(html) {
			alert("Cài Đặt Thành Công");
			location.reload();
		}
	});
}
</script>
<div class="content">
	<div id="public">
		Chọn bảng đánh giá : 
		<select id="selectloai" style="width: 470px">
			<c:forEach items="${lchs}" var="bang">
				<option value="${bang.id}">${bang.tenbang}</option>
			</c:forEach>
		</select>
		<div id="row">
			<input style="float: right ; width: 100px" id="button" type="button" onclick="setdanhgia('${pageContext.request.contextPath}/admin/setdanhgia/')" value="Chọn"/>
		</div>
	</div>
</div>