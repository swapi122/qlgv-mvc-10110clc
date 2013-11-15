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
			<div id="private">Bạn chưa được quyền đánh giá giảng viên</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="content" style="height: 100px"><h2>Phiếu Đánh Giá Chất Lượng Giảng Viên Khoa Đào Tạo Chất Lượng Cao</h2></div>
		<div class="content" style="width: 100%">
			<form
				action="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}"
				method="post">
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
							<div id="row"><c:forEach var="cauhoi" items="${lch.cauhois}">
									<div id="private" style="width: 830px">
										<div id="row">${cauhoi.noidung}</div>
										<div id="row">
											<ul>
												<li>A <input type="radio" value="A" name="${cauhoi.id}" /></li>
												<li>B <input type="radio" value="B" name="${cauhoi.id}" /></li>
												<li>C <input type="radio" value="C" name="${cauhoi.id}" /></li>
												<li>D <input type="radio" value="D" name="${cauhoi.id}" /></li>
											</ul>
										</div>
									</div>
								</c:forEach></div>
					</div>
				</c:forEach>
				<div id="row" style="margin-top: 10px; width: 100%" align="right"><input id="button" type="submit" value="Hoàn Thành" /></div>
			</form>
		</div>
	</c:otherwise>
</c:choose>
