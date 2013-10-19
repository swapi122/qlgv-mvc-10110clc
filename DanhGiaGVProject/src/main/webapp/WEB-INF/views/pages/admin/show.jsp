<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.nvh.giangvien.model.BangDanhGia"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>

    input.text , select { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:10px; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<script>
function deleteQuestion(URL){
	$.ajax({
		url : URL,
		type : 'POST',
		success : function(){
			alert("Xoa thanh cong");
			location.reload();
		}
	});
}
;
	$(function() {
		$("#dialog-form")
				.dialog(
						{
							autoOpen : false,
							height : 350,
							width : 350,
							modal : true,
							buttons : {
								"Chấp nhận" : function() {
									formData = $('#inputForm').serialize();
									$.ajax({
										url : $("#inputForm").attr('action'),
										type : 'POST',
										data: formData,
										success : function(){
											alert("Thành công");
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
								
							}
						});

		$(".btn_them").button().click(function() {
			$("#inputForm").attr("action","${pageContext.request.contextPath}/admin/question");
			$("#id").val("");
			$("#noidung").val("");
			$("typequestion").val(1);
			$("#dialog-form").dialog("open");
		});
	});
function updateQuestion(idquestion,contentQuestion,idtype){
	$("#id").val(idquestion);
	$("#noidung").val(contentQuestion);
	$("typequestion").val(idtype);
	$("#inputForm").attr("action","${pageContext.request.contextPath}/admin/question/"+idquestion+"?updatequestion");
	$("#dialog-form").dialog("open");
}
</script>

<div class="content">


	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus" href="${pageContext.request.contextPath}/admin?qldg">Danh	Sách Bảng Đánh Giá</a> &gt; <a class="focus"
			href="${pageContext.request.contextPath}/admin/${bangdanhgia.id}">Bảng Đánh Giá</a>
		<hr class="line-header-padding" />
	</div>
	<c:if test="${not empty bangdanhgia}">
		<div id="dialog-form" title="Tạo câu hỏi">
			<p class="validateTips">Điền đầy đủ thông tin</p>
			<form id="inputForm" method="post" action="">
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
		<div id="public">
			<div id="row">Bảng : ${bangdanhgia.id}</div>
			<div id="row">
				<img
					src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
				Tên bảng : ${bangdanhgia.tenbang}
			</div>
			<div id="row" align="right">
				<input id="button" class="btn_them" type="button"
					value="Thêm Câu Hỏi" />
			</div>
		</div>
		<c:forEach items="${bangdanhgia.cauhois}" var="cauhoi">
			<div id="private">
				<div id="row">Mã Câu : ${cauhoi.id}</div>
				<div id="row" style="font-weight: bold;">
					<img
						src="${pageContext.request.contextPath}/resources/images/link16x16_1.png" />
					Loại Câu Hỏi : ${cauhoi.loaicau.tenloai}
				</div>
				<div id="row">
					<img
						src="${pageContext.request.contextPath}/resources/images/webpage16x16_1.png" />
					Nội Dung : ${cauhoi.noidung}
				</div>
				<div id="row" align="right">
					<input id="button" type="button" value="Sữa" onclick="updateQuestion('${cauhoi.id}','${cauhoi.noidung}','${cauhoi.loaicau.id}')" /> <input id="button"
						type="button" value="Xóa" onclick="deleteQuestion('${pageContext.request.contextPath}/admin/question/${cauhoi.id}?bangid=${bangdanhgia.id}')" />
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>