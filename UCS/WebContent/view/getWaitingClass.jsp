<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="Domain.CLS_WAITING"%>
<%@ page import="Dao.daoCLS_WAITING"%>
<%@ page import="Dao.daoCLS_WAITING_Q"%>
<%@ page import="Domain.CM_USERINF"%>
<%@ page import="Dao.daoCM_USERINF"%>
<%@ page import="Util.StringUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date(); // 현재 시간

	String strTitle = "Untact Class System";
	String strService = "[Get Waiting Room Information]";
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

	ArrayList<CLS_WAITING> list = new ArrayList<CLS_WAITING>();
	CLS_WAITING cls = new CLS_WAITING();
	daoCLS_WAITING_Q dao = new daoCLS_WAITING_Q();

	if (strGRP_ID.equals("ADMIN_USER")) {
		cls.setCNCL_YN("N");
		list = dao.getRecordByUser(cls);
	} else if (strGRP_ID.equals("OPEN_USER")) {
		cls.setOPEN_USER(strUSER_ID);
		list = dao.getRecordByUser(cls);
	} else if (strGRP_ID.equals("RESV_USER")) {
		cls.setRESV_USER(strUSER_ID);
		cls.setCNCL_YN("N");
		list = dao.getRecordByUser(cls);
	} else {
		msg = "권한이 없습니다.";
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
</head>
<body>

	<!-- page -->
	<div data-role="page" id="get_passinfo_page">

		<!-- header -->
		<div data-role="header" data-position="fixed" data-theme="b">

			<a href="../view/myInfo.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="info"><%=strUSER_ID%></a>
			<h1>
				<a href="../view/getClass.jsp" rel="external" data-ajax="false"><%=strTitle%></a>
			</h1>
			<a href="../view/login.jsp" rel="external" data-ajax="false"
				data-role="button" data-icon="arrow-l">Logout</a>

			<!-- Menu -->
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

		<!-- content -->
		<div data-role="content">
			<!-- 리스트 출력-->
			<ul data-role="listview" data-divider-theme="b" data-inset="true"
				data-filter="true" data-filter-placeholder="검색 키워드를 입력하세요.">
				<%
					if (list != null) {

						String targetPage = "../view/findWaitingClass.jsp";
						String strURL = "";
						String strUSR_ID = "";
						String strSITE_ID = "";
						String strWAIT_DATE = "";
						String strCLASS_NO = "";
						String strWAIT_NO = "";
						
						for (int i = 0; i < list.size(); i++) {
							cls = list.get(i);
							strWAIT_DATE = URLEncoder.encode(cls.getWAIT_DATE(), "UTF-8");
							strCLASS_NO = URLEncoder.encode(cls.getCLASS_NO(), "UTF-8");
							strWAIT_NO = URLEncoder.encode(String.valueOf(cls.getWAIT_NO()), "UTF-8");
							strURL = targetPage + "?WAIT_DATE=" + strWAIT_DATE + "&CLASS_NO=" + strCLASS_NO + "&WAIT_NO=" + strWAIT_NO;
				%>
				<li><a href="<%=strURL%>" rel="external" data-ajax="false">

						<h3>
							Room.
							<%=cls.getCLASS_NO()%>
							(
							<%=cls.getCLASS_NM()%>
							)
						</h3>

						<p>
							<span>장소 </span><span><%=cls.getCLASS_ADDR()%></span>
						</p>

						<p>
							<span>대기번호 </span><span><%=cls.getWAIT_NO()%></span>
						</p>
						<p>
							<%
								if (stringUtil.toCheck(cls.getUSE_YN())) {
							%>
							<span>이용 날짜 <%=stringUtil.strDateToDateYMDHMS(cls.getUSE_DT())%>
							</span>
							<%
								}
							%>
						</p>

						<p>
							<%
								if (stringUtil.toCheck(cls.getCNCL_YN())) {
							%>
							<span>취소 날짜 <%=stringUtil.strDateToDateYMDHMS(cls.getCNCL_DT())%>
							</span>
							<%
								}
							%>
						</p>
						<p class="ui-li-aside"><%=stringUtil.strDateToDateYMDHMS(cls.getRESV_DT())%></p>
				</a></li>
				<%
					}
					}
				%>
			</ul>

		</div>

		<!-- footer -->
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h4>&copy; ICIA ACADEMY 왜 나가조?</h4>
		</div>

	</div>

</body>
</html>