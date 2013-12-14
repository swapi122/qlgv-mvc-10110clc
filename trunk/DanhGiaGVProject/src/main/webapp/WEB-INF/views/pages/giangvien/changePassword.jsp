<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>
<!-- ajax de send request change password -->
	function checkpass(){
		if($("#mkmoi").val() == $("#mkmoinhaplai").val()){
			return true;
		}else{
			return false;
		}
	}
	function changepass() {
		if (checkpass()) {
			$.ajax({
				url : "${pageContext.request.contextPath}/changepwd",
				type : 'POST',
				data : {
					iduser : "${account.id}",
					pwdold : $("#mkhientai").val(),
					pwdnew : $("#mkmoi").val()
				},
				success : function(data) {
					alert(data);
				}
			});
		}else{
			alert("Mật khẩu nhập lại không trùng với mật khẩu mới");
		}
	};
</script>

<div class="content">
	<div class="site-map-path">
		<a href="${pageContext.request.contextPath}/sinhvien"><img
			src="${pageContext.request.contextPath}/resources/images/home.png"
			height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label> <a
			class="focus"
			href="${pageContext.request.contextPath}/sinhvien/changePassword">Đổi
			mật khẩu</a>
		<hr class="line-header-padding" />
	</div>
	<div id="public">
		<div id="row">
			<table>
				<tr>
					<td>Mật khẩu hiện tại :</td>
					<td><input type="text" id="mkhientai" name="mkhientai" /></td>
				</tr>
				<tr>
					<td>Mật khẩu mới :</td>
					<td><input type="text" id="mkmoi" name="mkmoi" /></td>
				</tr>
				<tr>
					<td>Nhập lại mật khẩu mới :</td>
					<td><input type="text" id="mkmoinhaplai" name="mkmoinhaplai" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input style="width: 150px"
						id="button" type="button" onclick="changepass()" value="Thay đổi" /></td>
				</tr>
			</table>
		</div>
	</div>
</div>