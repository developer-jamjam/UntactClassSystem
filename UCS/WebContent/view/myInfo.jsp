<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="Domain.CM_USERINF"%>
<%@ page import="Dao.daoCM_USERINF"%>
<%@ page import="Domain.CM_COMMCD"%>
<%@ page import="Dao.daoCM_COMMCD"%>
<%@ page import="Util.StringUtil"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date(); // 현재 시간

	String strTitle = "Untact Class System";
	String strService = "[Find ?? Information]"; //Find Room Information
	String msg = "";
	String redirectURL = "";

	// 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
	if (session.getAttribute("USER_ID") == null) {
		response.sendRedirect("../view/login.jsp");
	}

	String strUSER_ID = session.getAttribute("USER_ID").toString();
	String strGRP_ID = session.getAttribute("GRP_ID").toString();
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

	// find User
	CM_USERINF clsCM_USERINF = new CM_USERINF();
	daoCM_USERINF daoCM_USERINF = new daoCM_USERINF();

	clsCM_USERINF.setUSER_ID(strUSER_ID);

	clsCM_USERINF = daoCM_USERINF.findRecord(clsCM_USERINF);

	if (clsCM_USERINF.getCOUNT() == 0) {
		response.sendRedirect("../view/login.jsp");
	}
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
<!-- 회원정보 수정 버튼 -->
	function UpdateUser() {
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

		if (!regExpPW.test(pw)) {
			alert("비밀번호 : 4~18 문자로 입력해 주세요.");
			pw.focus();
			return;
		} else if (!regExpPH.test(hp)) {
			alert("전화번호 : 8~11자리 숫자로 입력해 주세요.");
			ph.focus();
			return;
		} else {
			frm.action = '../service/update_userinfo.jsp';
			frm.method = 'post';
			frm.target = '';
			frm.submit();
		}
	}

	function InactiveUser() {
		if (confirm("회원탈퇴하시겠습니까?")) {
			var frm = document.frminfo;

			frm.action = '../service/inactive_userinfo.jsp';
			frm.method = 'post';
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

			<a href="../view/myInfo.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="info"><%=strUSER_ID%></a>
			<h1>
				<a href="../view/getRoom.jsp" rel="external" data-ajax="false"><%=strTitle%></a> <!-- 20210423수정할 부분 -->
			</h1>
			<a href="../view/login.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="arrow-l">Logout</a>

			<div data-role="navbar">
				<%-- <ul>
					<%
						if (strUSER_ID.equals("") == false) {
					%>
					<li><a href="../view/getRoom.jsp" id="getRoom" <!-- 20210423수정할 부분 -->
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">방
							조회</a></li>
					<%
						if (strGRP_ID.equals("OPEN_USER") == false) {
					%>
					<li><a href="../view/getWaitingRoom.jsp" id="getWaitingRoom" <!-- 20210423수정할 부분 -->
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">예약
							조회</a></li>
					<%
						}
					%>
					<%
						if (strGRP_ID.equals("OPEN_USER")) {
					%>
					<li><a href="../view/insertRoom.jsp" <!-- 20210423수정할 부분 -->
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">방
							신청</a></li>
					<%
						}
						}
					%>
				</ul> --%>
			</div>

		</div>

		<!-- Content -->
		<div data-role="content">

			<!-- title -->
			<div class="ui-bar ui-bar-a">
				<h4>회원정보수정</h4>
			</div>

			<!-- content -->
			<div class="ui-body ui-body-a">

				<!-- 레코드 -->
				<form id="frminfo" name="frminfo" method="post">

					<table>
						<tr>
							<!-- ROOM DATE -->
							<td colspan="6"><select name="GRP_ID" id="GRP_ID"
								readonly="readonly">
									<%
										if (codeList != null) {

											String strCodeValue = "";
											String strCodeName = "";

											for (int i = 0; i < codeList.size(); i++) {
												code = codeList.get(i);

												strCodeValue = code.getCMCD_VAL();
												strCodeName = code.getCMCD_NM();
												if (code.getCMCD_ID().equals("GRP_ID")) {
													if (strCodeValue.equals(clsCM_USERINF.getGRP_ID())) {
									%>
									<option value="<%=strCodeValue%>"><%=strCodeName%></option>
									<%
										}
												}
											}
										}
									%>

							</select></td>
						</tr>
						<!-- CUST_ID -->
						<tr>
							<td colspan="3">아이디</td>
							<td colspan="3"><input type="text" id="USER_ID"
								name="USER_ID" value="<%=clsCM_USERINF.getUSER_ID()%>"
								data-mini="true" readonly="readonly" /></td>
						</tr>

						<!-- CUST_PASSWORD -->
						<tr>
							<td colspan="3">비밀번호</td>
							<td colspan="3"><input type="password" id="USER_PW"
								name="USER_PW" value="" data-mini="true" placeholder="Password" /></td>
						</tr>

						<!--CUST_NAME -->
						<tr>
							<td colspan="3">이름</td>
							<td colspan="3"><input type="text" id="USER_NM"
								name="USER_NM" value="<%=clsCM_USERINF.getUSER_NM()%>"
								data-mini="true" /></td>
						</tr>

						<!-- CUST_PH -->
						<tr>
							<td colspan="3">핸드폰번호</td>
							<td colspan="3"><input type="tel" id="USER_HP"
								name="USER_HP" value="<%=clsCM_USERINF.getUSER_HP()%>"
								data-mini="true" /></td>
						</tr>

						<!-- REMARK -->
						<tr>
							<td colspan="6"><textarea id="REMARK" name="REMARK"
									placeholder="기타사항"><%=clsCM_USERINF.getREMARK()%></textarea></td>
						</tr>

						<tr>
							<td colspan="6"><hr style="" /></td>
						</tr>

						<tr>
							<!-- Update button-->
							<td colspan="3"><input type="button" id="btnUpdate"
								name="btnUpdate" value="수정" data-mini="true"
								onclick="UpdateUser()" /></td>
							<td colspan="3"><input type="button" id="btnInactive"
								name="btnInactive" value="탈퇴" data-mini="true"
								onclick="InactiveUser()" /></td>
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