<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="content">
		<div class="site-map-path">
			<a href="${pageContext.request.contextPath}/admin"><img src="${pageContext.request.contextPath}/resources/images/home.png" height="18"
				width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
				class="focus" href="${pageContext.request.contextPath}/admin?qldg">Danh Sách Bảng Đánh Giá</a> &gt; <a
				class="focus" href="${pageContext.request.contextPath}/admin/${bangdanhgia.id}">Xem Bảng Đánh Giá</a>
			<hr class="line-header-padding" />
		</div>
		<c:if test="${not empty bangdanhgia}">
			<div id="public">
				<div id="row">Bảng : ${bangdanhgia.id}</div>
				<div id="row">
					<img
						src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
					Tên bảng : ${bangdanhgia.tenbang}
				</div>
				<div id="row" align="right">
					<input id="button" type="button" value="Thêm Câu Hỏi"/>
				</div>
			</div>
			<c:forEach items="${bangdanhgia.cauhois}" var="cauhoi">
				<div id="private">
				<div id="row">Mã Câu : ${cauhoi.id}</div>
				<div id="row" style="font-weight: bold;">
					<img
						src="${pageContext.request.contextPath}/resources/images/link16x16_1.png" />
					Loại Câu Hỏi : ${cauhoi.loaicau.tenloai}
				</div>
				<div id="row">
					<img
						src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
					Nội Dung : ${cauhoi.noidung}
				</div>
				<div id="row" align="right">
					<input id="button" type="button" value="Sữa"/> 
					<input id="button" type="button" value="Xóa"/>
				</div>
			</div>
			</c:forEach>
		</c:if>
</div>