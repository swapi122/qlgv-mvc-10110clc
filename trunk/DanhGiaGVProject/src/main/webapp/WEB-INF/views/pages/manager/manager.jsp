<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/admin"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label class="focus"> Trang Chủ </label> 
		<hr class="line-header-padding" />
	</div>

	<script>
		$(function() {
			$("#list").jqGrid({
				url : '/giangvien/manager/qlgvgrid',
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
		});
	</script>

	<div id="mysearch">
		<br />
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