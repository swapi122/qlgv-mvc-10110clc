<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="org.springframework.ui.Model,java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="content" >
	<script>
		$(function() {
			$("#list").jqGrid({
				url : '/giangvien/admin/qlusergrid',
				datatype : 'json',
				mtype : 'GET',
				colNames:[ "ID", "Ho Ten", "Loai Account" ],
				colModel:[{name:'id', index:'id', width:100}, 
				{name:'hoten', index:'hoten', width:150}, 
				{name:'typeaccount', index:'typeaccount', width:120}],
				jsonReader : {
					root : "userData",
					page : "currentPage",
					total : "totalPages",
					records : "totalRecords",
					repeatitems : false,
					id : "id"
				},
				pager : '#pager',
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				sortname : 'hoten',
				sortorder : 'asc',
				viewrecords : true,
				gridview : true,
				height : 300,
				width : 600,
				caption : 'Danh Sách User',
				onSelectRow : function(id) {
					document.location.href ="${showContactUrl}/" + id;
				}
			});
		});
	</script>

	<h2>Danh Sách User</h2>
	<div >
		<table id="list">
		</table>
	</div>
	<div id="pager"></div>
</div>