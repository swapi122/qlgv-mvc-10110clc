<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a class="focus"
					href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh
					giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private">Bạn chưa được quyền đánh giá giảng viên</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="content" style="height: 100px">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/sinhvien"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a class="focus"
					href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh
					giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<h2 style="float: left; color: #1C407D;">Phiếu Đánh Giá Chất
				Lượng Giảng Viên Khoa Đào Tạo Chất Lượng Cao</h2>
		</div>
		<c:choose>
			<c:when test="${not empty bangdanhgiakq }">
				<!-- da danh gia roi, gio show ket qua danh gia lan truoc -->
				<div class="content" style="width: 100%">
					<form id="#inputForm" method="post" action="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}?update">
						<div id="public" style="width: 860px">
							<div id="row">
								<b>${bangdanhgia.tenbang}</b>
							</div>
							<div id="row">
								<b>Đánh giá giảng viên : ${thoikhoabieu.gv.hoten}</b>
							</div>
							<div id="row">
								<b>Môn học : ${thoikhoabieu.monhoc.tenMH}</b>
							</div>
						</div>
						<input type="hidden" name="idtkb" value="${thoikhoabieu.id}" />
						<c:forEach var="lch" items="${lchs}">
							<div id="public" style="width: 860px">
								<b><u>Tiêu Chí ${lch.id} </u> : ${lch.tenloai}</b>
								<div id="row">
									<c:forEach var="kq" items="${bangdanhgiakq.cauhoikqs}">
										<c:if test="${lch.id == kq.cauhoi.loaicau.id}">
											<div id="private" style="width: 830px">
												<div id="row">${kq.cauhoi.noidung}</div>
												<div id="row">
													<ul>
														<c:choose>
															<c:when test="${kq.ketqua == 'A'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" checked /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'B'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" checked /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'C'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" checked /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'D'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" checked /></li>
															</c:when>
														</c:choose>
													</ul>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						<div id="row" style="margin-top: 10px; width: 100%" align="right">
							<input id="button" type="submit" value="Cập nhật" />
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="content" style="width: 100%">
					<form
						action="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}"
						method="post" onsubmit="return checkButons(this);">
						<div id="public" style="width: 860px">
							<div id="row">
								<b>${bangdanhgia.tenbang}</b>
							</div>
							<div id="row">
								<b>Đánh giá giảng viên : ${thoikhoabieu.gv.hoten}</b>
							</div>
							<div id="row">
								<b>Môn học : ${thoikhoabieu.monhoc.tenMH}</b>
							</div>
						</div>
						<input type="hidden" name="idtkb" value="${thoikhoabieu.id}" />
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
														<li>Rất Tốt <input type="radio" value="A"
															name="${cauhoi.id}" /></li>
														<li>Tốt <input type="radio" value="B"
															name="${cauhoi.id}" /></li>
														<li>Bình Thường <input type="radio" value="C"
															name="${cauhoi.id}" /></li>
														<li>Chưa Tốt <input type="radio" value="D"
															name="${cauhoi.id}" /></li>
													</ul>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						<div id="row" style="margin-top: 10px; width: 100%" align="right">
							<input id="button" type="submit" value="Hoàn Thành" />
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
