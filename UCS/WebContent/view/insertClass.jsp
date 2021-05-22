<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="Domain.CLS_CLASSINF"%>
<%@ page import="Dao.daoCLS_CLASSINF"%>
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
	String strService = "[Insert Class Information]";
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
	function applyRoom() {
		var frm = document.frmInfo;

		frm.action = '../service/insert_class.jsp';
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

		<!-- content -->
		<div data-role="content">
			<!-- 검색 결과 -->
			<ul data-role="listview" data-inset="true">

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
									name="CLASS_NO" value="" data-mini="true" placeholder="방 번호"
									readonly></td>

								<!-- OPEN USER -->
								<td colspan="1"><input type="text" id="OPEN_USER"
									name="OPEN_USER" value="<%=strUSER_ID%>" data-mini="true"
									placeholder="개설자" readonly></td>

								<!-- APPLY DATE-->
								<td colspan="1"><input type="date" id="APPLY_DT"
									name="APPLY_DT" value="<%=today%>" data-mini="true" readonly /></td>
							</tr>
							<tr>
								<!-- ROOM NAME -->
								<td colspan="1">방 이름</td>
								<td colspan="3"><input type="text" id="CLASS_NM"
									name="CLASS_NM" value="" data-mini="true" placeholder="방 이름"></td>

								<!-- OPEN DATE & CLOSE DATE-->
								<td colspan="2">시작일 & 종료일 (yyyy-MM-dd)</td>
							</tr>


							<tr>
								<!-- ROOM ADDRESS -->
								<td colspan="1">방 주소</td>
								<td colspan="3"><input type="text" id="CLASS_ADDR"
									name="CLASS_ADDR" data-mini="true" placeholder="방 주소"></td>

								<td colspan="1"><input type="date" id="OPEN_DATE"
									name="OPEN_DATE" value="<%=today%>" data-mini="true"></td>
								<!-- <td>-</td> -->
								<td colspan="1"><input type="date" id="CLOSE_DATE"
									name="CLOSE_DATE" value="<%=closeDate%>" data-mini="true" /></td>
							</tr>

							<tr>
								<!-- REMARK -->
								<td colspan="1">기타</td>
								<td colspan="5"><textarea id="REMARK" name="REMARK"
										data-mini="true" placeholder="기타사항"></textarea></td>
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
										%>
										<option value="<%=strCodeValue%>"><%=strCodeName%></option>
										<%
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
										%>
										<option value="<%=strCodeValue%>"><%=strCodeName%></option>
										<%
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
										%>
										<option value="<%=strCodeValue%>"><%=strCodeName%></option>
										<%
											}
												}
											}
										%>

								</select></td>

							</tr>

							<tr>
								<!-- CNCL_YN -->
								<td colspan="6"><input type="hidden" id="CNCL_YN"
									name="CNCL_YN" value="" data-mini="true"></td>
							</tr>
							<tr>
								<!-- CNCL DATE-->
								<td colspan="6"><input type="hidden" id="CNCL_DT"
									name="CNCL_DT" value="" data-mini="true" /></td>

							</tr>
							<tr>
								<!-- CNCL_USER -->
								<td colspan="6"><input type="hidden" id="CNCL_USER"
									name="CNCL_USER" value="" data-mini="true"></td>

							</tr>
							<tr>
								<!-- APPROVE_YN -->
								<td colspan="6"><input type="hidden" id="APPROVE_YN"
									name="APPROVE_YN" value="" data-mini="true"></td>
							</tr>
							<tr>
								<!-- APPROVE_DT-->
								<td colspan="6"><input type="hidden" id="APPROVE_DT"
									name="APPROVE_DT" value="" data-mini="true" /></td>
							</tr>
							<tr>
								<!-- APPROVE_USER -->
								<td colspan="6"><input type="hidden" id="APPROVE_USER"
									name="APPROVE_USER" value="" data-mini="true"></td>
							</tr>

							<tr>
								<!-- LAST_CHNG_USER -->
								<td colspan="6"><input type="hidden" id="LAST_CHNG_USER"
									name="LAST_CHNG_USER" value="" data-mini="true"></td>

							</tr>
							<tr>
								<!-- LAST_CHNG_DT-->
								<td colspan="6"><input type="hidden" id="LAST_CHNG_DT"
									name="LAST_CHNG_DT" value="" data-mini="true" /></td>
							</tr>

							<tr>
								<td colspan="6"><hr style="" /></td>
							</tr>

							<!-- 버튼 -->
							<tr>
								<td colspan="6"><input type="button" id="btnApply"
									name="btnApply" value="신청" onclick="applyRoom()" /></td>
							</tr>
						</table>


					</form>
				</div>
			</ul>
		</div>

		<!-- footer -->
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h4>&copy; ICIA ACADEMY 왜 나가조?</h4>
		</div>

	</div>

</body>
</html>