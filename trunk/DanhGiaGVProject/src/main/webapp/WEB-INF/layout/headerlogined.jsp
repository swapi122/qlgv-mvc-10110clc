<%@page import="com.nvh.giangvien.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<nav>
	<div class="inside nav">
		<ul id="topnav">
			<li><a href="${pageContext.request.contextPath}/login"> <img src="${pageContext.request.contextPath}/resources/images/home.png"
					border="0px" />
				<p>Trang Chủ</p>
			</a></li>
			<li><a href="${pageContext.request.contextPath}/logout"> <br /> Thoát <br />
					<span>(Chào: <%=((User)session.getAttribute("account")).getHoten()%>)<br /></span></a></li>
		</ul>
	</div>
</nav>
