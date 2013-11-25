<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus"
			href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh
			giá giảng viên</a>
		<hr class="line-header-padding" />
	</div>
	<c:choose>
		<c:when test="${not empty success}">
			<script type="text/javascript">
				new Messi('${success}' + '.Cảm ơn bạn đã tham gia việc góp phần nâng cao chất lượng giảng dạy của đội ngủ giảng viên');
			</script>
		</c:when>
		<c:when test="${not empty fail }">
			<script type="text/javascript">
				new Messi('${fail}' + '. Rất tiếc đã xãy ra lỗi, xin hãy chắc là bạn đã đánh giá đủ số lượng câu hỏi.');
			</script>
		</c:when>
	</c:choose>
	<br />
</div>