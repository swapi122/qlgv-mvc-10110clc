<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="bdgChoose"
	class="com.nvh.applicationscope.BangDanhGiaChoose" scope="application" />
<%
	bdgChoose.setId(19);
%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/sinhvien"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus" href="${pageContext.request.contextPath}/sinhvien/">Thời
			khóa biểu</a>
		<hr class="line-header-padding" />
	</div>
	<c:choose>
		<c:when test="${not empty success}">
			<script type="text/javascript">
				TINY.box
						.show({
							html : '${success}'
									+ '.Cảm ơn bạn đã tham gia việc góp phần nâng cao chất lượng giảng dạy của đội ngủ giảng viên',
							animate : false,
							close : false,
							boxid : 'error',
							top : 200
						});
			</script>
		</c:when>
		<c:when test="${not empty fail }">
			<script type="text/javascript">
				TINY.box
						.show({
							html : '${fail}'
									+ '. Rất tiếc đã xãy ra lỗi, xin hãy chắc là bạn đã đánh giá đủ số lượng câu hỏi.',
							animate : false,
							close : false,
							boxid : 'error',
							top : 200
						});
			</script>
		</c:when>
	</c:choose>
	<c:forEach items="${tkblist}" var="tkb">
		<a
			href="${pageContext.request.contextPath}/sinhvien/danhgia/${tkb.id}">
			<div id="public">
				<div id="row">
					<span style="text-decoration: underline; font-weight: bold;">Môn
						Học </span> : ${tkb.monhoc.tenMH}
				</div>
				<div id="row">
					<span style="text-decoration: underline; font-weight: bold;">Giảng
						viên </span> : ${tkb.gv.hoten}
				</div>
			</div>
		</a>
	</c:forEach>
</div>