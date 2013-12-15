<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
ul li {
	display: inline;
}

.percent {
	float: left;
	display: block;
	width: 191px;
	height: 50px;
	color: black;
	background-color: #dddddd;
	border-top: 1px solid black;
	border-bottom: 1px solid black;
	border-left: 1px solid black;
	text-align: center;
	vertical-align: middle;
	line-height: 50px;
}
</style>
<c:choose>
	<c:when test="${not empty error}">
		<div class="content">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/manager"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<%-- <a class="focus" href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}"> --%>Đánh
					giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private">${error}</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="content" style="height: 100px">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/gvien/"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a 
					href="${pageContext.request.contextPath}/manager/tracuugv">Tra cứu giảng viên</a> > 
				<a class="focus"
					href="${pageContext.request.contextPath}/manager/tracuugv/${gv.id}">Kết quả đánh giá ${gv.hoten}</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private" style="margin-top: 0px">
				<h2 style="float: left; color: #1C407D;">
					KẾT QUẢ ĐÁNH GIÁ <br />
					<br />
					<a href="${pageContext.request.contextPath}/download?iddg=${bangdanhgia.id}&mh=${mh.ID}&iduser=${gv.id}" >
						Xuất báo cáo
					</a>
				</h2>
				<span style="float: right; color: #1C407D; font-size: 14px;">
					<u>Giảng Viên :</u> ${gv.hoten} <br /> <br /> <u>Môn :</u>
					${mh.tenMH}
				</span>
			</div>
		</div>
		<div class="content" style="width: 100%">
			<c:forEach var="lch" items="${lchs}">
				<div id="public" style="width: 860px">
					<b><u>Tiêu Chí ${lch.id} </u> : ${lch.tenloai}</b>
					<div id="row">
						<c:forEach var="kq" items="${kqs}">
							<c:if test="${lch.cauhois.contains(kq.ch)}">
								<div id="private" style="width: 830px">
									<div id="row">${kq.ch.noidung}</div>
									<div id="row">
										<ul
											style="list-style-type: none; margin: 0; padding: 0; width: 830px">
											<li style="background-color: #196EEE ; width: ${((kq.numA/100 * 800) == 0) ? 0 : (kq.numA/100 * 770)}" class="percent">${((kq.numA/100 * 750) == 0) ? "" : kq.noidungA}</li>
											<li style="background-color: #DA4531 ; width: ${((kq.numB/100 * 800) == 0) ? 0 : (kq.numB/100 * 770)}" class="percent">${((kq.numB/100 * 750) == 0) ? "" : kq.noidungB}</li>
											<li style="background-color: #FFB700 ; width: ${((kq.numC/100 * 800) == 0) ? 0 : (kq.numC/100 * 770)}" class="percent">${((kq.numC/100 * 750) == 0) ? "" : kq.noidungC}</li>
											<li style="background-color: #009855 ; width: ${((kq.numD/100 * 800) == 0) ? 0 : (kq.numD/100 * 770)} " class="percent"
												style="border-right : 1px solid black;">${((kq.numD/100 * 750) == 0) ? "" : kq.noidungD}</li>
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