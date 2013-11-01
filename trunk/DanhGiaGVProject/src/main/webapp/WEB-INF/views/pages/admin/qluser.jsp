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
				colModel:[
				          {name:'id', label: 'ID', formatter:'string', width: 200},
				          {name:'hoten', label: 'Ho Tên', width: 300},
				          {name:'typeaccount', label: 'Loại Tài Khoản', width: 200}
				        ],
				height: 'auto',
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
					document.location.href ="/giangvien/admin/user/" + id;
				}
			});
			$('#mysearch').jqGrid('filterGrid', '#list', { 
	              filterModel: [
	                {label: 'ID', name: 'id'},
	                {label: 'Họ Tên', name: 'hoten'},
	                {label: 'Loại Tài Khoản', name : 'typeaccount'}
	              ],
	              formtype: 'vertical',
	              enableSearch: true,
	              enableClear: true,
	              autosearch: false
	      });
		});
	</script>
	<h2>Danh Sách User</h2>
	 <div id="mysearch"><br/></div>
    <br/>
    <div style="margin-top: 15px">
	<table id="list" >
	<tr><td/></tr>
	</table>
	</div>
	<div id="pager" ></div>
</div>