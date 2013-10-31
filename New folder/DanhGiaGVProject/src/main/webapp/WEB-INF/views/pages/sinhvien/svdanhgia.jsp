<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
ul li{
 display: inline;
}
</style>
<div class="content" style="width: 100%">
	<div id="public" style="width: 860px">
		<div id="row"><b>Bảng : ${bangdanhgia.id}</b></div>
		<div id="row"><b>Tên bảng : ${bangdanhgia.tenbang}</b></div>
		<div id="row"><b>Đánh giá giảng viên : ${thoikhoabieu.gv.hoten}</b></div>
		<div id="row"><b>Môn học : ${thoikhoabieu.monhoc.tenMH}</b></div>
	</div>
	<c:forEach var="cauhoi" items="${bangdanhgia.cauhois}">
		<div id="private" style="width: 880px">
			<div id="row">${cauhoi.noidung}</div>
			<div id="row">
				<ul>
					<li>A <input type="radio" value="A" name="${cauhoi.id}"/></li>
					<li>B <input type="radio" value="B" name="${cauhoi.id}"/></li>
					<li>C <input type="radio" value="C" name="${cauhoi.id}"/></li>
					<li>D <input type="radio" value="D" name="${cauhoi.id}"/></li>
				</ul>
			</div>
		</div>
	</c:forEach>
</div>