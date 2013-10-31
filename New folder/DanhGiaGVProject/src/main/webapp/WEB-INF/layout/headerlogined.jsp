<%@page import="com.nvh.giangvien.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav>
	<div class="inside nav">
		<ul id="topnav">

			<li><a href="login"> <img src="${pageContext.request.contextPath}/resources/images/home.png"
					border="0px" />
				<p>Trang Chủ</p>
			</a></li>
			<li><a href="${pageContext.request.contextPath}/login?logout"> <br /> Thoát <br />
					<span>(Chào: <%=((User)session.getAttribute("account")).getHoten() %> )</span></a></li>
		</ul>
	</div>
</nav>
