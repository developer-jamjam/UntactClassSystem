<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="Domain.CLS_CLASSINF"%>
<%@ page import="Dao.daoCLS_CLASSINF_Q"%>
<%@ page import="Domain.CM_USERINF"%>
<%@ page import="Dao.daoCM_USERINF"%>
<%@ page import="Util.StringUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date(); // 현재 시간

	String strTitle = "Waiting Management System";
	String strService = "[Get Room Information]";
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

	ArrayList<CLS_CLASSINF> list = new ArrayList<CLS_CLASSINF>();
	CLS_CLASSINF cls = new CLS_CLASSINF();
	daoCLS_CLASSINF_Q dao = new daoCLS_CLASSINF_Q();

	if (strGRP_ID.equals("ADMIN_USER")) {
		cls.setCNCL_YN("N");
		list = dao.getRoomList(cls);
	} else if (strGRP_ID.equals("OPEN_USER")) {
		cls.setOPEN_USER(strUSER_ID);
		list = dao.getRoomList(cls);
	} else if (strGRP_ID.equals("RESV_USER")) {
		cls.setCNCL_YN("N");
		cls.setAPPROVE_YN("Y");
		list = dao.getRoomList(cls);
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
<title>Waiting Management System</title>

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

		<!-- content -->
		<div data-role="content">

			<!-- 리스트 출력-->
			<ul data-role="listview" data-divider-theme="b" data-inset="true"
				data-filter="true" data-filter-placeholder="검색 키워드를 입력하세요.">
				<%
					if (list != null) {

						String targetPage = "../view/findClass.jsp";
						String strURL = "";
						String strUSR_ID = "";
						String strSITE_ID = "";
						String strCLASS_NO = "";

						for (int i = 0; i < list.size(); i++) {
							cls = list.get(i);

							strCLASS_NO = URLEncoder.encode(cls.getCLASS_NO(), "UTF-8");
							strURL = targetPage + "?CLASS_NO=" + strCLASS_NO;
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
						</p> <%
 	if (stringUtil.toCheck(cls.getAPPROVE_YN())) {
 %>
						<p>
							<span>승인 날짜 <%=stringUtil.strDateToDateYMDHMS(cls.getAPPROVE_DT())%>
							</span>
						</p> <%
 	}
 %> <%
 	if (stringUtil.toCheck(cls.getCNCL_YN())) {
 %><p>
							<span>취소 날짜 <%=stringUtil.strDateToDateYMDHMS(cls.getCNCL_DT())%>
							</span>
						</p> <%
 	}
 %>

						<p class="ui-li-aside"><%=stringUtil.strDateToDateYMDHMS(cls.getLAST_CHNG_DT())%></p>
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