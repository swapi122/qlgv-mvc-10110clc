<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
function setURL(id,gvid){
	$('#' + id).attr('href','${pageContext.request.contextPath}/manager/kqdanhgiagv/' + gvid + '?iddg='+$("#selectloai").val()+'&mmh=' +id);
}
</script>
<div class="content">
	<a href="${pageContext.request.contextPath}/gvien/"><img
		src="${pageContext.request.contextPath}/resources/images/home.png"
		height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
		class="focus"
		href="${pageContext.request.contextPath}/gvien/kqdanhgia/">Môn giảng dạy</a>
	<hr class="line-header-padding" />

	<c:choose>
		<c:when test="${not empty tkbs}">
			<div id="private">
				<u>Chọn bảng đánh giá muốn xem kết quả :</u>
				<select id="selectloai" style="width: 380px">
					<c:forEach items="${lchs}" var="bang">
						<option value="${bang.id}">${bang.tenbang}</option>
					</c:forEach>
				</select>
			</div>
			<div id="public">
			Các môn đang giảng dạy :
			<c:forEach items="${tkbs}" var="tkb">
				<a id="${tkb.ID}" href="#" onclick="setURL('${tkb.ID}',${gv.id})">
					<div id="private" style="width: 550px">
						${tkb.tenMH}
					</div>
				</a>
			</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<div id="public">
				<div id="row">Hiện tại giảng viên không có môn giảng dạy.</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>