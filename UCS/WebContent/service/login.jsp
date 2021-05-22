<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>

<%@ page import="Domain.CM_USERINF"%>
<%@ page import="Dao.daoCM_USERINF"%>

<%@ page import="Util.StringUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");

	StringUtil stringUtil = new StringUtil();
	Date now = new Date(); // 현재 시간

	String strService = "[Login Service]";
	String msg = "";
	String redirectURL = "";
%>

<%
	System.out.println(strService);

	// TEST USER ID SETTING
	String strUSER_ID = URLDecoder.decode(request.getParameter("USER_ID"), "UTF-8");
	String strUSER_PW = URLDecoder.decode(request.getParameter("USER_PW"), "UTF-8");

	System.out.println("User ID : " + strUSER_ID);
	System.out.println("User PW : " + strUSER_PW);

	// Validation
	daoCM_USERINF dao = new daoCM_USERINF();
	CM_USERINF cls = new CM_USERINF();

	// 데이터 셋팅
	cls.setUSER_ID(strUSER_ID);

	// Insert
	cls = dao.findRecord(cls);

	System.out.println("[Find User Info]");
	System.out.println("Count : " + cls.getCOUNT());
	System.out.println("User ID : " + cls.getUSER_ID());
	System.out.println("User PW : " + cls.getUSER_PW());

	if (cls.getCOUNT() == 0 || cls.getUSER_PW().compareTo(strUSER_PW) != 0) {
		System.out.println("[Login Failed]");
		msg = "아이디 또는 패스워드를 확인해주세요.";
		redirectURL = "../view/login.jsp";
	} else if (stringUtil.toCheck(cls.getINACTIVE_YN()) == true) {
		System.out.println("[Login Failed]");
		msg = "회원 정보가 없습니다.";
		redirectURL = "../view/login.jsp";
	} else {
		System.out.println("[Login Succeed]");
		msg = "";

		if (cls.getGRP_ID().equals("ADMIN_USER")) {
			redirectURL = "../view/getClass.jsp";
		} else if (cls.getGRP_ID().equals("OPEN_USER")) {
			redirectURL = "../view/getClass.jsp";
		} else if (cls.getGRP_ID().equals("RESV_USER")) {
			redirectURL = "../view/getWaitingClass.jsp";
		}

		// 세션
		session.setAttribute("USER_ID", cls.getUSER_ID());
		session.setAttribute("GRP_ID", cls.getGRP_ID());
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