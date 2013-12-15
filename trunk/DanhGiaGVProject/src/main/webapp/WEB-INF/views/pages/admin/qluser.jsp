<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.ui-pg-div {
	width: 50px !important;
}

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
<div class="content">
	<c:if test="${not empty dups }">
		<script>
			TINY.box.show({html:'Trùng ID của những User : ${dups}!',animate:false,close:false,boxid:'error',top:200});
		</script>
	</c:if>
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus"
			href="${pageContext.request.contextPath}/admin/qluserlist">Quản lý Users</a>
		<hr class="line-header-padding" />
	</div>

	<script>
		$(function() {
			$("#dialog-form").dialog({
				autoOpen : false,
				height : 400,
				width : 300,
				modal : true,
				buttons : {
					"Thêm" : function() {
						formData = $('#inputForm').serialize();
						$.ajax({
							url : $("#inputForm").attr("action"),
							type : 'POST',
							data : formData,
							success : function(data) {
								TINY.box.show({html: data,animate:false,close:false,boxid:'error',top:230});
							}
						});
						$(this).dialog("close");
					},
					Cancel : function() {
						$(this).dialog("close");
					}
				}
			});

			$("#list").jqGrid({
				url : '/giangvien/admin/qlusergrid',
				datatype : 'json',
				mtype : 'GET',
				colModel : [ {
					name : 'id',
					label : 'ID',
					formatter : 'string',
					width : 200
				}, {
					name : 'hoten',
					label : 'Ho Tên',
					width : 300
				}, {
					name : 'typeaccount',
					label : 'Loại Tài Khoản',
					width : 200
				} ],
				height : 'auto',
				jsonReader : {
					root : "userData",
					page : "currentPage",
					total : "totalPages",
					records : "totalRecords",
					repeatitems : false,
					id : "id"
				},
				pager : '#pager',
				rowNum : 20,
				rowList : [ 10, 20, 30 ],
				sortname : 'hoten',
				sortorder : 'asc',
				viewrecords : true,
				gridview : true,
				height : 300,
				width : 615,
				caption : 'Danh Sách User',
				onSelectRow : function(id) {
					document.location.href = "/giangvien/admin/user/" + id;
				}

			});
			$('#mysearch').jqGrid('filterGrid', '#list', {
				filterModel : [ {
					label : 'ID',
					name : 'id'
				}, {
					label : 'Họ Tên',
					name : 'hoten'
				}, {
					label : 'Loại Tài Khoản',
					name : 'typeaccount'
				} ],
				formtype : 'vertical',
				enableSearch : true,
				enableClear : true,
				autosearch : false
			});
			$("#list").navGrid('#pager', {
				edit : false,
				add : false,
				del : false,
				search : false
			}).navButtonAdd('#pager', {
				caption : "Add",
				buttonicon : "ui-icon-add",
				onClickButton : function() {
					openDialog();
				},
				position : "last"
			}).navButtonAdd('#pager', {
				caption : "Del",
				buttonicon : "ui-icon-del",
				onClickButton : function() {
					alert("Deleting Row");
				},
				position : "last"
			});
		});
		function openDialog() {
			$("#inputForm").attr("action",
					"${pageContext.request.contextPath}/admin/user");
			$("#dialog-form").dialog("open");
		};
	</script>

	<div id="dialog-form" title="Thêm User">
		<form id="inputForm" method="post">
			<fieldset>
				<label for="id">ID :</label> <input type="text" name="id" id="id"
					class="text ui-widget-content ui-corner-all" value="" /> <label
					for="hoten">Họ Tên :</label> <input type="text" name="hoten"
					id="hoten" value="" class="text ui-widget-content ui-corner-all" />
				<label for="noisinh">Nơi Sinh :</label> <input type="text"
					name="noisinh" id="noisinh" value=""
					class="text ui-widget-content ui-corner-all" /> <label
					for="ngaysinh">Ngày Sinh :</label> <input type="text"
					name="ngaysinh" id="ngaysinh" value=""
					class="text ui-widget-content ui-corner-all" /> <label
					for="loaiaccount">Loai Account :</label> <select name="loaiaccount"
					id="loaiaccount" class="text ui-widget-content ui-corner-all">
					<option value="0">Sinh Viên</option>
					<option value="1">Giảng Viên</option>
					<option value="2">Manager</option>
					<option value="3">Admin</option>
				</select>
			</fieldset>
		</form>
	</div>

	<div id="mysearch">
		<br />
	</div>
	<br />
	<div id="private" style="width: 580px">
		<div id="row" style="width: 580px">
			<form id="updateForm" method="post" enctype="multipart/form-data"
				action="/giangvien/admin/upload">
				<label for="file"> Upload File chứa danh sách sinh viên</label> <input
					type="file" name="fileUpload" size="50" /> <input type="submit" />
			</form>
		</div>
	</div>
	<br />
	<div style="margin-top: 15px">
		<table id="list">
			<tr>
				<td />
			</tr>
		</table>
	</div>
	<div id="pager"></div>
</div>