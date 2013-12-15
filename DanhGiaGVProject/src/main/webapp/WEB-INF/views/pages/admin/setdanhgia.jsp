<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
	function setdanhgia(URL) {
		if (checkTime()) {
			var result = confirm("Bạn có chắc là sẽ chọn bảng đánh giá này!");
			if (result == true) {
				$.ajax({
					url : URL + $("#selectloai").val(),
					data : {
						timebd : $('#timebdau').val().toString(),
						timekt : $('#timekthuc').val().toString()
					},
					type : 'POST',
					success : function(html) {
						TINY.box.show({html:'Cài Đặt Thành Công!',animate:true,close:true,boxid:'error',top:200});
					}
				});
			}
		}
	}
	function checkTime() {
		var stringdaybd = $('#timebdau').val().toString();
		var stringdaykt = $('#timekthuc').val().toString();
		if (stringdaybd == "" || stringdaykt == "") {
			TINY.box.show({
				html : 'Hãy chọn thời gian bắt đầu và thời gian kết thúc!',
				animate : false,
				close : false,
				boxid : 'error',
				top : 250
			});
			return false;
		}
		var BigDaybd = new Date(stringdaybd);
		var BigDaykt = new Date(stringdaykt);
		var leftTime = (BigDaykt.getTime() - BigDaybd.getTime());
		if (leftTime <= 0) {
			TINY.box
					.show({
						html : '[Không hợp lệ] Hãy chọn thời gian bắt đầu trước thời gian kết thúc!',
						animate : false,
						close : false,
						boxid : 'error',
						top : 250
					});
			return false;
		} else {
			return true;
		}
	}
</script>
<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus"
			href="${pageContext.request.contextPath}/admin/setdanhgia">Cài
			đặt bảng đánh giá</a>
		<hr class="line-header-padding" />
	</div>
	<div id="public">
		<div id="row">
			<table>
				<tr>
					<td>Chọn bảng đánh giá :</td>
					<td><select id="selectloai" style="width: 455px">
							<c:forEach items="${lchs}" var="bang">
								<option value="${bang.id}">${bang.tenbang}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Ngày bắt đầu :</td>
					<td><input type="text" id="timebdau" /></td>
				</tr>
				<tr>
					<td>Ngày kết thúc :</td>
					<td><input type="text" id="timekthuc" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input style="width: 150px"
						id="button" type="button"
						onclick="setdanhgia('${pageContext.request.contextPath}/admin/setdanhgia/')"
						value="Chọn" /></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/resources/script/datetimepicker/jquery.datetimepicker.js"></script>
<script>
	//2013-11-29 00:00:00
	$('#timebdau').datetimepicker({
		format : 'Y-m-d H:i:00'
	});
	$('#timekthuc').datetimepicker({
		format : 'Y-m-d H:i:00'
	});
</script>