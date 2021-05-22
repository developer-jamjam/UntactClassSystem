<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	// 세션 초기화
	session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1.0, width=device-width">
<title>Untact Class System</title>
<style>
* {
	text-align: center;
}
</style>
<!-- 제이쿼리 모바일, 제이쿼리 라이브러리 파일 -->
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript">
	function Login() {
		var frm = document.frmLogin;

		frm.action = '../service/login.jsp';
		frm.target = '';
		frm.submit();

	}

	function Join() {
		var frm = document.frmLogin;

		frm.action = '../view/join.jsp';
		frm.target = '';
		frm.submit();

	}
</script>
</head>

<body>
	<!-- page -->
	<div data-role="page" id="appaly_page">

		<!-- header -->
		<div data-role="header" data-position="fixed" data-theme="b">

			<!-- Title -->
			<h1>Untact Class System</h1>

		</div>

		<!-- content -->
		<div data-role="content">

			<form id="frmLogin" name="frmLogin" method="post">

				<h2>Login</h2>

				<!-- ID -->
				<label>ID</label> <input type="text" id="USER_ID" name="USER_ID" value=""
					data-mini="true">

				<!-- PASSWORD -->
				<label>Password</label> <input type="password" id="USER_PW" name="USER_PW"
					value="" data-mini="true">

				<!-- 버튼 -->
				<div class="ui-body">
					<fieldset class="ui-grid-a">

						<!-- Login -->
						<div class="ui-block-a">
							<input type="button" id="btnLogin" name="btnLogin" value="Login"
								onclick="Login()" />
						</div>

						<!-- Join -->
						<div class="ui-block-b">
							<input type="button" id="btnJoin" name="btnJoin" value="Join"
								onclick="Join()" />
						</div>
					</fieldset>
				</div>
			</form>

		</div>


		<!-- footer -->
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h6>&copy; ICIA ACADEMY 왜 나가조? </h6>
		</div>

	</div>

</body>

</html>
