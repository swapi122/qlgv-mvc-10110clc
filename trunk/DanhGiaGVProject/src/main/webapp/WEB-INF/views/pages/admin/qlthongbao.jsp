<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>
input.text,select {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 10px;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
$(function() {
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 250,
		width : 350,
		modal : true,
		buttons : {
			"Đăng thông báo" : function() {
				formData = $('#inputForm').serialize();
				$.ajax({
					url : $("#inputForm").attr("action"),
					type : 'POST',
					data : formData,
					success : function(data) {
						alert(data);
						location.reload();
					}
				});
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");
		}
	});
});
function openDialogCreate() {
	$("#dialog-form").dialog("open");
};
</script>
<div class="site-map-path">
	<a href="${pageContext.request.contextPath}/admin"><img
		src="${pageContext.request.contextPath}/resources/images/home.png"
		height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
		class="focus" href="${pageContext.request.contextPath}/admin?qldg">Bảng
		tin thông báo</a>
	<div class="add-course">
		<a href="#" onclick="openDialogCreate()"><img alt="Tạo thông báo"
			title="Tạo thông báo." height="24" width="24" border="0"
			src="${pageContext.request.contextPath}/resources/images/add.png" /></a>
	</div>
	<hr class="line-header-padding" />
</div>
<div class="content">
	<div id="dialog-form" title="Tạo thông báo">
		<form id="inputForm" action="${pageContext.request.contextPath}/admin/qlthongbao" method="post">
			<fieldset>
				<label for="id">ID :</label> <input type="text" name="id" id="id"
					class="text ui-widget-content ui-corner-all"
					value="" /> <label for="noidung">Nội dung thông báo :</label> <input
					type="text" name="noidung" id="noidung" value=""
					class="text ui-widget-content ui-corner-all" />
			</fieldset>
		</form>
	</div>
	<c:choose>
		<c:when test="${not empty tbs }">
			<c:forEach var="tb" items="${tbs}">
				<div id="public" style="width: 600px">
					<div id="row"><u>Thông báo :</u> ${tb.id}</div>
					<div id="row"><b>Nội dung :</b> ${tb.tenthongbao}</div>
					<div id="row"><b>Ngày đăng thông báo :</b> ${tb.ngaytao}</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="private">Chưa đăng bất kỳ thông báo nào!</div>
		</c:otherwise>
	</c:choose>
</div>