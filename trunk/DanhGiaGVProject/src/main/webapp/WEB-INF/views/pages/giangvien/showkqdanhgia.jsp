<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>
ul li {
	display: inline;
}
</style>

<c:choose>
	<c:when test="${not empty error}">
		<div class="content">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/admin"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
					class="focus" href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private">Bạn chưa được quyền đánh giá giảng viên</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="content" style="height: 100px">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/admin"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
					class="focus" href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<h2 style="float:left ; color: #1C407D;">Phiếu Đánh Giá Chất Lượng Giảng Viên Khoa Đào Tạo Chất Lượng Cao</h2>
		</div>
		<div class="content" style="width: 100%">
				<c:forEach var="lch" items="${lchs}">
					<div id="public" style="width: 860px">
							<b><u>Tiêu Chí ${lch.id} </u> : ${lch.tenloai}</b>
							<div id="row">
								<c:forEach var="cauhoi" items="${lch.cauhois}">
									<c:if test="${bangdanhgia.cauhois.contains(cauhoi)}">
										<div id="private" style="width: 830px">
											<div id="row">${cauhoi.noidung}</div>
											<div id="row">
												<ul>
													<li>A : </li>
													<li>B <input type="radio" value="B" name="${cauhoi.id}" /></li>
													<li>C <input type="radio" value="C" name="${cauhoi.id}" /></li>
													<li>D <input type="radio" value="D" name="${cauhoi.id}" /></li>
												</ul>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
					</div>
				</c:forEach>
		</div>
	</c:otherwise>
</c:choose>