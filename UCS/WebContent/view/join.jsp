<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%-- <%@ page import="Domain.RM_ROOMINF"%>
<%@ page import="Dao.daoRM_ROOMINF"%> --%>
<%@ page import="Domain.CM_COMMCD"%>
<%@ page import="Dao.daoCM_COMMCD"%>
<%@ page import="Util.StringUtil"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date(); // 현재 시간

	String strService = "[Join]";
	String msg = "";
	String redirectURL = "";
%>

<%
	System.out.println(strService);

	//Today
	String today = stringUtil.dtToStrYMD(now);
	String closeDate = "2099-12-31";

	// COMMON CODE
	ArrayList<CM_COMMCD> codeList = new ArrayList<CM_COMMCD>();
	CM_COMMCD code = new CM_COMMCD();
	daoCM_COMMCD daoCM_COMMCD = new daoCM_COMMCD();

	// COMMON CODE ALL LIST
	codeList = daoCM_COMMCD.getRecord(code);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1.0, width=device-width">
<title>Untact Class System</title>

<!-- 제이쿼리 모바일, 제이쿼리 라이브러리 파일 -->
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
<!-- 회원가입 확인 버튼 -->
	function Join() {
		var frm = document.frminfo;

		var regExpID = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 아이디는 문자로 시작
		var regExpPW = /^[a-z0-9_-]{4,18}$/; // 영문, 숫자, 언더스코어(_), 하이픈(-)이 포함된 6~18 문자
		var regExpPWCHECK = /^[a-z0-9_-]{6,18}$/; // 영문, 숫자, 언더스코어(_), 하이픈(-)이 포함된 6~18 문자
		var regExpNAME = /^[가-힣]+$/; // 이름은 한글만 입력
		var regExpPH = /^[0-9]{8,11}$/; // 연락처 0부터 9까지 8~11자리 숫자
		var regExpBOD = /^[0-9]{6,6}$/; // 생년월일

		var id = frm.USER_ID.value;
		var pw = frm.USER_PW.value;
		var name = frm.USER_NM.value;
		var hp = frm.USER_HP.value;

		if (!regExpID.test(id)) {
			alert("아이디는 문자로 시작해 주세요.");
			id.select();
			return;
		} else if (!regExpPW.test(pw)) {
			alert("비밀번호 : 4~18 문자로 입력해 주세요.");
			pw.focus();
			return;
		} else if (!regExpPH.test(hp)) {
			alert("전화번호 : 8~11자리 숫자로 입력해 주세요.");
			ph.focus();
			return;
		} else {
			frm.action = '../service/join.jsp';
			frm.target = '';
			frm.submit();
		}
	}
</script>
</head>
<body>

	<!-- Page -->
	<div data-role="page" id="update_passinfo_page">

		<!-- header -->
		<div data-role="header" data-position="fixed" data-theme="b">

			<!-- Title -->
			<h1>Untact Class System</h1>

		</div>

		<!-- Content -->
		<div data-role="content">

			<!-- title -->
			<div class="ui-bar ui-bar-a">
				<h4>회원가입</h4>
			</div>

			<!-- content -->
			<div class="ui-body ui-body-a">

				<!-- 레코드 -->
				<form id="frminfo" name="frminfo" method="post">

					<table>
						<!-- GROPU NUMBER -->

						<tr>
							<td colspan="1">유저 타입</td>
							<!-- ROOM DATE -->
							<td colspan="3"><select name="GRP_ID" id="GRP_ID">
									<%
										if (codeList != null) {

											String strCodeValue = "";
											String strCodeName = "";

											for (int i = 0; i < codeList.size(); i++) {
												code = codeList.get(i);

												strCodeValue = code.getCMCD_VAL();
												strCodeName = code.getCMCD_NM();
												if (code.getCMCD_ID().equals("GRP_ID")) {
									%>
									<option value="<%=strCodeValue%>"><%=strCodeName%></option>
									<%
										}
											}
										}
									%>

							</select></td>
						</tr>
						<!-- CUST_ID -->
						<tr>
							<td>아이디</td>
							<td><input type="text" id="USER_ID" name="USER_ID"
								data-mini="true" /></td>
						</tr>

						<!-- CUST_PASSWORD -->
						<tr>
							<td>비밀번호</td>
							<td><input type="password" id="USER_PW" name="USER_PW"
								data-mini="true" /></td>
						</tr>

						<!--CUST_NAME -->
						<tr>
							<td>이름</td>
							<td><input type="text" id="USER_NM" name="USER_NM"
								data-mini="true" /></td>
						</tr>

						<!-- CUST_PH -->
						<tr>
							<td>핸드폰번호</td>
							<td><input type="tel" id="USER_HP" name="USER_HP"
								data-mini="true" /></td>
						</tr>



						<tr>
							<!-- Join button -->
							<td><input type="button" id="btnJoin" name="btnJoin"
								value="가입" data-mini="true" onclick="Join()" /></td>
							<!-- Cancel button-->
							<td><input type="button" id="btnCancel" name="btnCancel"
								value="취소" data-mini="true"
								onclick="location.href='../view/login.jsp';" /></td>
						</tr>


						<tr>
							<td colspan="6"><hr style="" /></td>
						</tr>

					</table>

				</form>
			</div>
		</div>

		<!-- footer -->
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h4>&copy; ICIA ACADEMY 왜 나가조?</h4>
		</div>

	</div>

</body>
</html>