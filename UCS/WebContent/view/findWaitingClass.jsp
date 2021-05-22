<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="Domain.CLS_WAITING"%>
<%@ page import="Dao.daoCLS_WAITING_Q"%>
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
	String strService = "[Find Reserve Room Information]";
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
	String today = stringUtil.dtToStrDate(now);
	String closeDate = "2099-12-31";

	// COMMON CODE
	ArrayList<CM_COMMCD> codeList = new ArrayList<CM_COMMCD>();
	CM_COMMCD code = new CM_COMMCD();
	daoCM_COMMCD daoCM_COMMCD = new daoCM_COMMCD();

	// COMMON CODE ALL LIST
	codeList = daoCM_COMMCD.getRecord(code);

	// 데이터 검색
	String strWAIT_DATE = URLDecoder.decode(request.getParameter("WAIT_DATE"), "UTF-8");
	String strROOM_NO = URLDecoder.decode(request.getParameter("ROOM_NO"), "UTF-8");
	int intWAIT_NO = Integer.parseInt(URLDecoder.decode(request.getParameter("WAIT_NO"), "UTF-8"));
	
	CLS_WAITING cls = new CLS_WAITING();
	daoCLS_WAITING_Q dao = new daoCLS_WAITING_Q();

	
	cls.setROOM_NO(strROOM_NO);
	cls.setWAIT_NO(intWAIT_NO);
	
	cls = dao.findRecordByRoom(cls);

	// 없으면
	if (cls.getCOUNT() == 0) {

		msg = "데이터가 없습니다.";
		redirectURL = "../view/getClass.jsp";

		// 스크립트
		out.write("<script>");

		if (msg != "") {

			// 알림창
			out.write("alert('" + msg + "');");

		}

		// 페이지 이동
		out.write("location.href='" + redirectURL + "';");

		// 스크립트 끝
		out.write("</script>");

	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1.0, width=device-width">
<title>Waiting Management System</title>

<!-- 제이쿼리 모바일, 제이쿼리 라이브러리 파일 -->
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
	function CancelReserve() {
		var frm = document.frmInfo;

		frm.action = '../service/cancel_waitingclass.jsp';
		frm.method = 'post';
		frm.target = '';
		frm.submit();
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
				<a href="../view/getRoom.jsp" rel="external" data-ajax="false"><%=strTitle%></a>
			</h1>
			<a href="../view/login.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="arrow-l">Logout</a>

			<div data-role="navbar">
				<ul>
					<%
						if (strUSER_ID.equals("") == false) {
					%>
					<li><a href="../view/getClass.jsp" id="getRoom"
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">방
							조회</a></li>
					<%
						if (strGRP_ID.equals("OPEN_USER") == false) {
					%>
					<li><a href="../view/getWaitingClass.jsp" id="getWaitingRoom"
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">예약
							조회</a></li>
					<%
						}
					%>
					<%
						if (strGRP_ID.equals("OPEN_USER")) {
					%>
					<li><a href="../view/insertClass.jsp"
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">방
							신청</a></li>
					<%
						}
						}
					%>
				</ul>
			</div>

		</div>

		<!-- Content -->
		<div data-role="content">

			<!-- title -->
			<div class="ui-bar ui-bar-a">
				<h4>예약 조회</h4>
			</div>

			<!-- content -->
			<div class="ui-body ui-body-a">

				<!-- 레코드 -->
				<form id="frmInfo" name="frmInfo" method="post">
					<input type="hidden" name="TRAN_ID" value="RM2002"> <input
						type="hidden" name="SCRN_ID" value="RM2002">
					<table>
						<tr>
							<!-- ROOM NUMBER -->
							<td colspan="1">방 번호</td>
							<td colspan="2"><input type="text" id="ROOM_NO"
								name="ROOM_NO" value="<%=cls.getROOM_NO()%>" data-mini="true"
								readonly></td>
							<td colspan="1"></td>
							<!-- OPEN USER -->
							<td colspan="2"><input type="text" id="OPEN_USER"
								name="OPEN_USER" value="<%=cls.getOPEN_USER()%>"
								data-mini="true" readonly></td>
						</tr>
						<tr>
							<!-- ROOM NAME -->
							<td colspan="1">방 이름</td>
							<td colspan="5"><input type="text" id="CLASS_NM"
								name="CLASS_NM" value="<%=cls.getCLASS_NM()%>" data-mini="true"
								readonly></td>
						</tr>


						<tr>
							<!-- ROOM ADDRESS -->
							<td colspan="1">방 주소</td>
							<td colspan="5"><input type="text" id="CLASS_ADDR"
								name="CLASS_ADDR" value="<%=cls.getCLASS_ADDR()%>"
								data-mini="true" readonly></td>
						</tr>

						<tr>
							<!-- ROOM ADDRESS -->
							<td colspan="1">기간</td>
							<td colspan="2"><input type="date" id="OPEN_DATE"
								name="OPEN_DATE"
								value="<%=stringUtil.strToStrDate(cls.getOPEN_DATE())%>"
								data-mini="true" readonly></td>
							<td colspan="1"></td>
							<td colspan="2"><input type="date" id="CLOSE_DATE"
								name="CLOSE_DATE"
								value="<%=stringUtil.strToStrDate(cls.getCLOSE_DATE())%>"
								data-mini="true" readonly></td>
						</tr>

						<tr>
							<!-- REMARK -->
							<td colspan="1">기타</td>
							<td colspan="5"><textarea id="REMARK" name="REMARK"
									data-mini="true" readonly><%=cls.getREMARK()%></textarea></td>
						</tr>

						<tr>
							<td colspan="1">방 시간</td>
							<!-- ROOM DATE -->
							<td colspan="3"><input type="text" id="CLASS_DATE"
								name="CLASS_DATE" value="<%=cls.getCLASS_DATE_NM()%>"
								data-mini="true" readonly></td>

							<!-- TIME -->
							<td colspan="2"><input type="text" id="CLASS_TIME"
								name="CLASS_TIME" value="<%=cls.getCLASS_TIME_NM()%>"
								data-mini="true" readonly></td>

						</tr>


						<tr>
							<td colspan="6"><hr style="" /></td>
						</tr>

						<%
							if (strGRP_ID.equals("RESV_USER")) {
						%>
						<tr>
							<td colspan="5"><input type="text" id="WAIT_DATE"
								name="WAIT_DATE"
								value="<%=stringUtil.strToStrDate(strWAIT_DATE)%>"
								data-mini="true" readonly></td>
							<td colspan="1"><input type="text" id="WAIT_NO"
								name="WAIT_NO" value="<%=intWAIT_NO%>" data-mini="true"
								readonly></td>
						</tr>

						<tr>
							<td colspan="6"><textarea id="REMARK_R" name="REMARK_R"
									data-mini="true" readonly><%=cls.getREMARK()%></textarea></td>
						</tr>
						<tr>
							<td colspan="6"><input type="button" id="btnCancel"
								name="btnCancel" value="예약 취소" onclick="CancelReserve()" /></td>
						</tr>
						<%
							}
						%>

					</table>

				</form>
			</div>
		</div>

		<div data-role="content"></div>

		<!-- footer -->
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h4>&copy; ICIA ACADEMY 왜 나가조?</h4>
		</div>

	</div>

</body>
</html>