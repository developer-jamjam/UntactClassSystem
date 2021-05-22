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

	String strService = "[Update User Information Service]";
	String msg = "";
	String redirectURL = "";
%>

<%
	System.out.println(strService);

	// TEST USER ID SETTING
	String strGRP_ID = URLDecoder.decode(request.getParameter("GRP_ID"), "UTF-8");
	String strUSER_ID = URLDecoder.decode(request.getParameter("USER_ID"), "UTF-8");
	String strUSER_PW = URLDecoder.decode(request.getParameter("USER_PW"), "UTF-8");
	String strUSER_NM = URLDecoder.decode(request.getParameter("USER_NM"), "UTF-8");
	String strUSER_HP = URLDecoder.decode(request.getParameter("USER_HP"), "UTF-8");
	String strREMARK = URLDecoder.decode(request.getParameter("REMARK"), "UTF-8");
	
	System.out.println("User ID : " + strUSER_ID);
	System.out.println("User PW : " + strUSER_PW);

	// Validation
	daoCM_USERINF dao = new daoCM_USERINF();
	CM_USERINF cls = new CM_USERINF();

	// 데이터 셋팅
	cls.setUSER_ID(strUSER_ID);

	// Insert
	cls = dao.findRecord(cls);

	if (cls.getCOUNT() == 0) {
		System.out.println("[Update Failed]");
		msg = "유저 정보가 없습니다.";
		redirectURL = "../view/myInfo.jsp";
	} else {
		System.out.println("[Update Succeed]");
		msg = "";
		
		//cls.setUSER_ID(strUSER_ID);
		cls.setUSER_PW(strUSER_PW);
		cls.setUSER_NM(strUSER_NM);
		cls.setUSER_HP(strUSER_HP);
		//cls.setGRP_ID(strGRP_ID);
		//cls.setINACTIVE_YN("N");
		//cls.setINACTIVE_DATE("");
		cls.setREMARK(strREMARK);
		cls.setLAST_CHNG_USER(strUSER_ID);
		cls.setLAST_CHNG_DT(stringUtil.dtToStrYMDHMS(now));

		dao.updateRecord(cls);
		
		msg = "회원정보수정되었습니다.";
		redirectURL = "../view/myInfo.jsp";
		
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