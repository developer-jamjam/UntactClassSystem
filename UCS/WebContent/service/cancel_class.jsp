<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>

<%@ page import="Domain.CLS_CLASSINF"%>
<%@ page import="Dao.daoCLS_CLASSINF"%>

<%@ page import="Util.StringUtil"%>
<%@ page import="Util.SeqUtil"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date();	// 현재 시간

	String strService = "[Delete Class Service]";
	String msg = "";
	String redirectURL = "";

	// 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
	if (session.getAttribute("USER_ID") == null) {
		response.sendRedirect("../view/login.jsp");
	}

	String strUSER_ID = session.getAttribute("USER_ID").toString();
%>

<%
	System.out.println(strService);

	String strCLASS_NO = URLDecoder.decode(request.getParameter("CLASS_NO"), "UTF-8");
	
	System.out.println("CLASS_NO : " + strCLASS_NO);

	// Validation
	daoCLS_CLASSINF dao = new daoCLS_CLASSINF();
	CLS_CLASSINF cls = new CLS_CLASSINF();

	cls.setCLASS_NO(strCLASS_NO);

	cls = dao.findRecord(cls);

	if (cls.getCOUNT() == 0) {
		msg = "방 정보가 없습니다.";
		redirectURL = "../view/getClass.jsp";
	} else {

		// 데이터 셋팅
		cls.setCLASS_NO(strCLASS_NO);
		cls.setCNCL_YN("Y");
		cls.setCNCL_DT(stringUtil.dtToStrYMDHMS(now));
		cls.setCNCL_USER(strUSER_ID);
		cls.setLAST_CHNG_USER(strUSER_ID);
		cls.setLAST_CHNG_DT(stringUtil.dtToStrYMDHMS(now));

		// Update
		dao.updateRecord(cls);

		//
		msg = "방 취소가 완료되었습니다.";
		redirectURL = "../view/getClass.jsp";
	}

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

	//}
%>