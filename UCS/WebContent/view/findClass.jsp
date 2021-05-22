<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="Domain.CLS_CLASSINF"%>
<%@ page import="Dao.daoCLS_CLASSINF"%>
<%@ page import="Domain.CLS_WAITING"%>
<%@ page import="Dao.daoCLS_WAITING"%>
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
	String strService = "[Find Class Information]";
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

	CLS_CLASSINF cls = new CLS_CLASSINF();
	daoCLS_CLASSINF dao = new daoCLS_CLASSINF();

	// 데이터 검색
	String strROOM_NO = URLDecoder.decode(request.getParameter("CLASS_NO"), "UTF-8");

	cls.setCLASS_NO(strROOM_NO);
	cls = dao.findRecord(cls);

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
<title>Untact class System</title>

<!-- 제이쿼리 모바일, 제이쿼리 라이브러리 파일 -->
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
	function btnUpdate() {
		var frm = document.frmInfo;

		frm.action = '../service/update_class.jsp';
		frm.method = 'post';
		frm.target = '';
		frm.submit();
	}

	function btnCancel() {
		var frm = document.frmInfo;

		frm.action = '../service/cancel_class.jsp';
		frm.method = 'post';
		frm.target = '';
		frm.submit();
	}

	function btnApprove() {
		var frm = document.frmInfo;

		frm.action = '../service/approve_class.jsp';
		frm.method = 'post';
		frm.target = '';
		frm.submit();
	}

	function btnReserve() {
		var frm = document.frmInfo;

		frm.action = '../service/reserve_class.jsp';
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
				<a href="../view/getClass.jsp" rel="external" data-ajax="false"><%=strTitle%></a>
			</h1>
			<a href="../view/login.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="arrow-l">Logout</a>

			<div data-role="navbar">
				<ul>
					<%
						if (strUSER_ID.equals("") == false) {
					%>
					<li><a href="../view/getClass.jsp" id="getClass"
						class="ui-btn ui-btn-inline" rel="external" data-ajax="false">방
							조회</a></li>
					<%
						if (strGRP_ID.equals("OPEN_USER") == false) {
					%>
					<li><a href="../view/getWaitingClass.jsp" id="getWaitingClass"
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
				<h4>방신청</h4>
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
							<td colspan="3"><input type="text" id="CLASS_NO"
								name="CLASS_NO" value="<%=cls.getCLASS_NO()%>" data-mini="true"
								readonly></td>

							<!-- OPEN USER -->
							<td colspan="1"><input type="text" id="OPEN_USER"
								name="OPEN_USER" value="<%=cls.getOPEN_USER()%>"
								data-mini="true" readonly></td>

							<!-- APPLY DATE-->
							<td colspan="1"><input type="date" id="APPLY_DT"
								name="APPLY_DT"
								value="<%=stringUtil.strToStrDate(cls.getAPPLY_DT())%>"
								data-mini="true" readonly /></td>
						</tr>
						<tr>
							<!-- ROOM NAME -->
							<td colspan="1">방 이름</td>
							<td colspan="3"><input type="text" id="CLASS_NM"
								name="CLASS_NM" value="<%=cls.getCLASS_NM()%>" data-mini="true"></td>

							<!-- OPEN DATE & CLOSE DATE-->
							<td colspan="2">시작일 & 종료일 (yyyy-MM-dd)</td>
						</tr>


						<tr>
							<!-- ROOM ADDRESS -->
							<td colspan="1">방 주소</td>
							<td colspan="3"><input type="text" id="CLASS_ADDR"
								name="CLASS_ADDR" value="<%=cls.getCLASS_ADDR()%>"
								data-mini="true"></td>

							<td colspan="1"><input type="date" id="OPEN_DATE"
								name="OPEN_DATE"
								value="<%=stringUtil.strToStrDate(cls.getOPEN_DATE())%>"
								data-mini="true"></td>

							<td colspan="1"><input type="date" id="CLOSE_DATE"
								name="CLOSE_DATE"
								value="<%=stringUtil.strToStrDate(cls.getCLOSE_DATE())%>"
								data-mini="true"></td>
						</tr>

						<tr>
							<!-- REMARK -->
							<td colspan="1">기타</td>
							<td colspan="5"><textarea id="REMARK" name="REMARK"
									data-mini="true"><%=cls.getREMARK()%></textarea></td>
						</tr>

						<tr>
							<td colspan="1">방 시간</td>
							<!-- ROOM DATE -->
							<td colspan="3"><select name="CLASS_DATE" id="CLASS_DATE">
									<%
										if (codeList != null) {

											String strCodeValue = "";
											String strCodeName = "";

											for (int i = 0; i < codeList.size(); i++) {
												code = codeList.get(i);

												strCodeValue = code.getCMCD_VAL();
												strCodeName = code.getCMCD_NM();

												if (code.getCMCD_ID().equals("CLASS_DATE")) {
													if (strCodeValue.compareTo(cls.getCLASS_DATE()) == 0) {
									%>
									<option value="<%=strCodeValue%>" selected="selected"><%=strCodeName%></option>
									<%
										} else {
									%>
									<option value="<%=strCodeValue%>"><%=strCodeName%></option>
									<%
										}
												}
											}
										}
									%>

							</select></td>

							<!-- TIME -->
							<td colspan="2"><select name="CLASS_TIME" id="CLASS_TIME">
									<%
										if (codeList != null) {

											String strCodeValue = "";
											String strCodeName = "";

											for (int i = 0; i < codeList.size(); i++) {
												code = codeList.get(i);

												strCodeValue = code.getCMCD_VAL();
												strCodeName = code.getCMCD_NM();

												if (code.getCMCD_ID().equals("CLASS_TIME")) {
													if (strCodeValue.compareTo(cls.getCLASS_TIME()) == 0) {
									%>
									<option value="<%=strCodeValue%>" selected="selected"><%=strCodeName%></option>
									<%
										} else {
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
						<tr>
							<!-- CALL YN -->
							<td colspan="1">호출 타입</td>
							<td colspan="3">
								<fieldset data-role="controlgroup" data-type="horizontal">
									<label for="CALL_Y">Y</label> <input type="radio"
										name="CALL_YN" id="CALL_Y" value="Y" /> <label for="CALL_N">N</label>
									<input type="radio" name="CALL_YN" id="CALL_N" value="N"
										checked="checked" />
								</fieldset>
							</td>

							<!-- CALL TYPE -->
							<td colspan="2"><select name="CALL_TYPE" id="CALL_TYPE">
									<%
										if (codeList != null) {

											String strCodeValue = "";
											String strCodeName = "";

											for (int i = 0; i < codeList.size(); i++) {
												code = codeList.get(i);

												strCodeValue = code.getCMCD_VAL();
												strCodeName = code.getCMCD_NM();

												if (code.getCMCD_ID().equals("CALL_TYPE")) {
													if (strCodeValue.compareTo(cls.getCALL_TYPE()) == 0) {
									%>
									<option value="<%=strCodeValue%>" selected="selected"><%=strCodeName%></option>
									<%
										} else {
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

						<tr>
							<!-- CNCL_YN -->
							<td colspan="6"><input type="hidden" id="CNCL_YN"
								name="CNCL_YN" value="<%=cls.getCNCL_YN()%>" data-mini="true"></td>
						</tr>
						<tr>
							<!-- CNCL DATE-->
							<td colspan="6"><input type="hidden" id="CNCL_DT"
								name="CNCL_DT" value="<%=cls.getCNCL_DT()%>" data-mini="true"></td>

						</tr>
						<tr>
							<!-- CNCL_USER -->
							<td colspan="6"><input type="hidden" id="CNCL_USER"
								name="CNCL_USER" value="<%=cls.getCNCL_USER()%>"
								data-mini="true"></td>

						</tr>
						<tr>
							<!-- APPROVE_YN -->
							<td colspan="6"><input type="hidden" id="APPROVE_YN"
								name="APPROVE_YN" value="<%=cls.getAPPROVE_YN()%>"
								data-mini="true"></td>
						</tr>
						<tr>
							<!-- APPROVE_DT-->
							<td colspan="6"><input type="hidden" id="APPROVE_DT"
								name="APPROVE_DT" value="<%=cls.getAPPROVE_DT()%>"
								data-mini="true"></td>
						</tr>
						<tr>
							<!-- APPROVE_USER -->
							<td colspan="6"><input type="hidden" id="APPROVE_USER"
								name="APPROVE_USER" value="<%=cls.getAPPROVE_USER()%>"
								data-mini="true"></td>
						</tr>

						<tr>
							<!-- LAST_CHNG_USER -->
							<td colspan="6"><input type="hidden" id="LAST_CHNG_USER"
								name="LAST_CHNG_USER" value="<%=cls.getLAST_CHNG_USER()%>"
								data-mini="true"></td>

						</tr>
						<tr>
							<!-- LAST_CHNG_DT-->
							<td colspan="6"><input type="hidden" id="LAST_CHNG_DT"
								name="LAST_CHNG_DT" value="<%=cls.getLAST_CHNG_DT()%>"
								data-mini="true"></td>
						</tr>
						<tr>
							<td colspan="6"><hr style="" /></td>
						</tr>

						<%
							if (strGRP_ID.equals("RESV_USER")) {
						%>
						<tr>
							<td colspan="6"><input type="date" id="WAIT_DATE"
								name="WAIT_DATE" value="<%=today%>" data-mini="true"></td>
						</tr>
						<tr>
							<td colspan="6"><textarea id="REMARK_R" name="REMARK_R"
									data-mini="true"></textarea></td>
						</tr>
						<%
							}
						%>
						<!-- 버튼 -->
						<tr>
							<%
								if (strGRP_ID.equals("ADMIN_USER")) {
							%>
							<td colspan="@"><input type="button" id="approve"
								name="approve" value="승인" onclick="btnApprove()" /></td>
							<td colspan="2"><input type="button" id="cancel"
								name="cancel" value="취소" onclick="btnCancel()" /></td>
							<%
								} else if (strGRP_ID.equals("OPEN_USER")) {
							%>
							<td colspan="2"><input type="button" id="update"
								name="update" value="수정" onclick="btnUpdate()" /></td>
							<td colspan="2"><input type="button" id="cancel"
								name="cancel" value="취소" onclick="btnCancel()" /></td>
							<%
								} else if (strGRP_ID.equals("RESV_USER")) {
							%>
							<td colspan="2"><input type="button" id="reserve"
								name="reserve" value="신청" onclick="btnReserve()" /></td>
							<%
								} else {
									msg = "권한이 없습니다.";
									response.sendRedirect("../view/login.jsp");
								}
							%>
						</tr>

						<tr>
							<td colspan="6"><hr style="" /></td>
						</tr>


					</table>


					<%
						if (strGRP_ID.equals("ADMIN_USER") || strGRP_ID.equals("OPEN_USER")) {

							ArrayList<CLS_WAITING> waitingList = new ArrayList<CLS_WAITING>();
							CLS_WAITING clsCLS_WAITING = new CLS_WAITING();
							daoCLS_WAITING_Q daoCLS_WAITING_Q = new daoCLS_WAITING_Q();

							clsCLS_WAITING.setCLASS_NO(cls.getCLASS_NO());
							clsCLS_WAITING.setCNCL_YN("N");
							System.out.println("FDSFDSF");
							waitingList = daoCLS_WAITING_Q.getRecordByRoom(clsCLS_WAITING);

							if (waitingList != null) {
					%><table border=1>
						<thead>
							<tr>
								<th>예약일</th>
								<th>대기번호</th>
								<th>예약자</th>
								<th>HP</th>
								<th>이용일</th>
							</tr>
						</thead>
						<tbody>
							<%
								CLS_WAITING clsRoom = new CLS_WAITING();
										for (int i = 0; i < waitingList.size(); i++) {
											clsRoom = waitingList.get(i);
							%>

							<tr>
								<td><%=stringUtil.strToStrDate(clsRoom.getWAIT_DATE())%></td>
								<td><%=clsRoom.getWAIT_NO()%></td>
								<td><%=clsRoom.getRESV_USER()%></td>
								<td><%=clsRoom.getRESV_USER_HP()%></td>
								<td><%=clsRoom.getUSE_DT()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%
						}
						}
					%>
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