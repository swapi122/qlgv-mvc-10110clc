<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="login">
	<script>
		TINY.box
				.show({
					html : 'BẠN KHÔNG CÓ QUYỀN TRUY CẬP VÀO NHỮNG TRANG NÀY!<br />VUI LÒNG SỬ DỤNG ĐÚNG QUYỀN HẠN CỦA MÌNH',
					animate : true,
					close : false,
					boxid : 'error',
					top : 200
				});
	</script>
	<form id="frmlogin" method="post"
		action='<c:url value="/j_spring_security_check" />'>
		<fieldset>
			<legend>Login</legend>
			User Name : <input id="textbox" type="text" name="j_username"
				class="textfield"><br /> Password : <input id="textbox"
				type="password" name="j_password" class="textfield"> <br />
			<br /> <input id="button" type="submit" class="btn_search"
				value="Login">
		</fieldset>
	</form>
	<div class="title">
		<img
			src="${pageContext.request.contextPath}/resources/images/logo_1.png" />
		<h1>PHẦN MỀM ĐÁNH GIÁ GIẢNG VIÊN</h1>
		<h2>ĐẠI HỌC SƯ PHẠM KỸ THUẬT TP.HỒ CHÍ MINH</h2>
	</div>
</div>