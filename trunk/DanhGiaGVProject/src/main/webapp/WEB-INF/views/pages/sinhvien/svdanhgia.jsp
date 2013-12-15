<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<style>
ul li {
	display: inline;
}
</style>
<script>
	var checkNames = new Array();
	var num = 0;
	var result = new Array();

	var resultBool = false;
	function setalo(data) {
		if (data == "false") {
			TINY.box.show({
				html : 'Mã xác thực không đúng! Vui lòng nhập lại',
				animate : false,
				close : false,
				boxid : 'error',
				top : 200
			});
			resultBool = false;
		} else {
			resultBool = true;
		}
	}

	function isValidCapCha() {
		formData = $('.formDanhGia').serialize();

		$.ajax({
			url : '${pageContext.request.contextPath}/sinhvien/checkCapcha',
			type : 'GET',
			data : formData,
			success : function(data) {
				setalo(data);
			}
		});

		return resultBool;
	}

	function isValidForm() {
		//check capcha
		var resultBoolValid = true;
		for ( var i = 0; i < checkNames.length; i++) {
			$("." + checkNames[i]).css("background-color", "#DFE4EE");
			result[i] = new Array(4);
			var flag = false;
			for ( var j = 0; j < 4; j++) {
				result[i][j] = document.getElementsByName(checkNames[i])[j].checked;
				if (result[i][j] == true) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				//co 1 check box chua dc check
				$("." + checkNames[i]).css("background-color", "red");
				$("." + checkNames[i]).css("box-shadow",
						"10px 10px 5px #888888");
				resultBoolValid = false;
			}
		}
		return resultBoolValid;
	}
</script>

<c:choose>
	<c:when test="${not empty error}">
		<div class="content">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/sinhvien"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a class="focus"
					href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh
					giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private">${error}</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="content" style="height: 100px">
			<div class="site-map-path">
				<a href="${pageContext.request.contextPath}/sinhvien"><img
					src="${pageContext.request.contextPath}/resources/images/home.png"
					height="18" width="18" border="0px" /></a> <label> Trang Chủ ></label>
				<a class="focus"
					href="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}">Đánh
					giá giảng viên</a>
				<hr class="line-header-padding" />
			</div>
			<div id="private">
				<h2 style="float: left; color: #1C407D;">Phiếu Đánh Giá Chất
					Lượng Giảng Viên Khoa Đào Tạo Chất Lượng Cao</h2>
			</div>
		</div>
		<c:choose>
			<c:when test="${not empty bangdanhgiakq }">
				<!-- da danh gia roi, gio show ket qua danh gia lan truoc -->
				<div class="content" style="width: 100%">
					<form id="#inputForm" method="post" class="formDanhGia"
						action="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}?update" onsubmit="return isValidCapCha()">
						<div id="public" style="width: 860px">
							<div id="row">
								<b>${bangdanhgia.tenbang}</b>
							</div>
							<div id="row">
								<b>Đánh giá giảng viên : ${thoikhoabieu.gv.hoten}</b>
							</div>
							<div id="row">
								<b>Môn học : ${thoikhoabieu.monhoc.tenMH}</b>
							</div>
						</div>
						<input type="hidden" name="idtkb" value="${thoikhoabieu.id}" />
						<c:forEach var="lch" items="${lchs}">
							<div id="public" style="width: 860px">
								<b><u>Tiêu Chí ${lch.id} </u> : ${lch.tenloai}</b>
								<div id="row">
									<c:forEach var="kq" items="${bangdanhgiakq.cauhoikqs}">
										<c:if test="${lch.id == kq.cauhoi.loaicau.id}">
											<div id="private" style="width: 830px">
												<div id="row">${kq.cauhoi.noidung}</div>
												<div id="row">
													<ul>
														<c:choose>
															<c:when test="${kq.ketqua == 'A'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" checked /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'B'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" checked /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'C'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" checked /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" /></li>
															</c:when>
															<c:when test="${kq.ketqua == 'D'.charAt(0)}">
																<li>Rất Tốt <input type="radio" value="A"
																	name="${kq.id}" /></li>
																<li>Tốt <input type="radio" value="B"
																	name="${kq.id}" /></li>
																<li>Bình Thường <input type="radio" value="C"
																	name="${kq.id}" /></li>
																<li>Chưa Tốt <input type="radio" value="D"
																	name="${kq.id}" checked /></li>
															</c:when>
														</c:choose>
													</ul>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						<div id="row" style="margin-top: 10px; width: 100% ;">
							<div style="width: 318px ;">
							<%
								ReCaptcha c = ReCaptchaFactory.newReCaptcha(
														"6Lds0OsSAAAAAHBd9GiU29wrZ9w6nJL0e_3gUnEx",
														"6Lds0OsSAAAAAEQvu-fQETRqdTGYE9V9miUemZLR",
														false);
												out.print(c.createRecaptchaHtml(null, null));
							%>
							<input id="button" type="submit" value="Cập nhật" />
							</div>
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="content" style="width: 100%">
					<form
						action="${pageContext.request.contextPath}/sinhvien/danhgia/${bangdanhgia.id}"
						method="post" onsubmit="return isValidForm()">
						<div id="public" style="width: 860px">
							<div id="row">
								<b>${bangdanhgia.tenbang}</b>
							</div>
							<div id="row">
								<b>Đánh giá giảng viên : ${thoikhoabieu.gv.hoten}</b>
							</div>
							<div id="row">
								<b>Môn học : ${thoikhoabieu.monhoc.tenMH}</b>
							</div>
						</div>
						<input type="hidden" name="idtkb" value="${thoikhoabieu.id}" />
						<c:forEach var="lch" items="${lchs}">
							<div id="public" style="width: 860px">
								<b><u>Tiêu Chí ${lch.id} </u> : ${lch.tenloai}</b>
								<div id="row">
									<c:forEach var="cauhoi" items="${lch.cauhois}">
										<c:if test="${bangdanhgia.cauhois.contains(cauhoi)}">
											<div id="private" style="width: 830px" class="${cauhoi.id}">
												<div id="row">${cauhoi.noidung}</div>
												<div id="row">
													<ul>
														<li>Rất Tốt <input type="radio" value="A"
															name="${cauhoi.id}" /></li>
														<li>Tốt <input type="radio" value="B"
															name="${cauhoi.id}" /></li>
														<li>Bình Thường <input type="radio" value="C"
															name="${cauhoi.id}" /></li>
														<li>Chưa Tốt <input type="radio" value="D"
															name="${cauhoi.id}" /></li>
														<script>
															checkNames[num] = "${cauhoi.id}";
															num++;
														</script>
													</ul>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						<div id="row" style="margin-top: 10px; width: 100%" align="right">
							<input id="button" type="submit" value="Hoàn Thành" />
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
