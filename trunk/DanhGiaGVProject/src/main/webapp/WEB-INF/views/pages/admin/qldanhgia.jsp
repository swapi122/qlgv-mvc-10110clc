<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<spring:url value="/admin" var="showBangUrl" />
<script>
	function deleteFunction(URL) {
		$.ajax({ 
			url: URL, 
			type: 'POST', 
			success: function(html) { 
				alert("Xóa Thành Công");
				location.reload();
			} 
		});
	}
</script>

<div class="site-map-path">
	<a href="${pageContext.request.contextPath}/admin"><img
		src="${pageContext.request.contextPath}/resources/images/home.png"
		height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
		class="focus" href="${pageContext.request.contextPath}/admin?qldg">Danh	Sách Bảng Đánh Giá</a>
	<div class="add-course">
		<a href="${pageContext.request.contextPath}/admin?form"><img
			alt="Tạo Bảng Đánh Giá" title="Tạo bảng đánh giá." height="24"
			width="24" border="0"
			src="${pageContext.request.contextPath}/resources/images/add.png" /></a>
	</div>
	<hr class="line-header-padding" />
</div>
<div class="content">
	<c:if test="${not empty danhsachdg}">
		<c:forEach var="bang" items="${danhsachdg}">
			<div id="private">
				<div id="row">Bảng : ${bang.id}</div>
				<div id="row">
					<img
						src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
					Tên bảng : <a href="${showBangUrl}/${bang.id}">${bang.tenbang}</a>
				</div>
				<div id="row" align="right">
					<input class="update${bang.id}" id="button" type="button"
						value="Sữa" /> <input class="delete${bang.id}" id="button"
						type="button" value="Xóa" onclick="deleteFunction('${pageContext.request.contextPath}/admin/${bang.id}')"/>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>