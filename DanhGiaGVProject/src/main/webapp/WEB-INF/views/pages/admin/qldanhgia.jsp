<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<spring:url value="/admin" var="showBangUrl" />
<style>
    input.text , select { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:10px; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<script>
	function deleteFunction(URL) {
		$.ajax({ 
			url: URL, 
			type: 'POST', 
			success: function(html) { 
				alert("Xóa Thành Công");
				location.reload();
			} 
		});
	}
	$(function() {
		var id = $("#id"), email = $("#email"), password = $("#password"), allFields = $(
				[]).add(name).add(email).add(password), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}

		$("#dialog-form")
				.dialog(
						{
							autoOpen : false,
							height : 350,
							width : 350,
							modal : true,
							buttons : {
								"Update" : function() {
									formData = $('#inputForm').serialize();
									$.ajax({
										url : $("#inputForm").attr('action'),
										type : 'POST',
										data: formData,
										success : function(){
											alert("Update thành công");
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

		$(".update").button().click(function() {
			$("#dialog-form").dialog("open");
		});
	});
</script>

<div class="site-map-path">
	<a href="${pageContext.request.contextPath}/admin"><img
		src="${pageContext.request.contextPath}/resources/images/home.png"
		height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
		class="focus" href="${pageContext.request.contextPath}/admin?qldg">Danh	Sách Bảng Đánh Giá</a>
	<div class="add-course">
		<a href="${pageContext.request.contextPath}/admin?form"><img
			alt="Tạo Bảng Đánh Giá" title="Tạo bảng đánh giá." height="24"
			width="24" border="0"
			src="${pageContext.request.contextPath}/resources/images/add.png" /></a>
	</div>
	<hr class="line-header-padding" />
</div>
<div class="content">
	<c:if test="${not empty danhsachdg}">
		<div id="dialog-form" title="Cập nhật bảng đánh giá">
			<form id="inputForm" method="post" action="${pageContext.request.contextPath}/admin/question"">
				<fieldset>
					<input name="bangid" id="bangid" type="hidden" value="${bangdanhgia.id}"/>
					<label for="id">ID</label> <input type="text" name="id" id="id"
						class="text ui-widget-content ui-corner-all" /> <label
						for="noidung">Nội dung</label> <input type="text" name="noidung"
						id="noidung" value="" class="text ui-widget-content ui-corner-all" />
					<label for="typequestion">Loại Câu Hỏi</label><br ><select name="typequestion"
						id="typequestion" class="text ui-widget-content ui-corner-all">
						<c:forEach items="${dslch}" var="lch">
							<option value="${lch.id}">${lch.tenloai}</option>
						</c:forEach>
					</select>
				</fieldset>
			</form>
		</div>
	
		<c:forEach var="bang" items="${danhsachdg}">
			<div id="private">
				<div id="row">Bảng : ${bang.id}</div>
				<div id="row">
					<img
						src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
					Tên bảng : <a href="${showBangUrl}/${bang.id}">${bang.tenbang}</a>
				</div>
				<div id="row" align="right">
					<input class="update" id="button" type="button"
						value="Sữa" /> <input id="button"
						type="button" value="Xóa" onclick="deleteFunction('${pageContext.request.contextPath}/admin/${bang.id}')"/>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>