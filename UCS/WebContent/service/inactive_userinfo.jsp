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

	String strUSER_ID = URLDecoder.decode(request.getParameter("USER_ID"), "UTF-8");
	
	System.out.println("User ID : " + strUSER_ID);

	// Validation
	daoCM_USERINF dao = new daoCM_USERINF();
	CM_USERINF cls = new CM_USERINF();

	// 데이터 셋팅
	cls.setUSER_ID(strUSER_ID);

	// Insert
	cls = dao.findRecord(cls);

	if (cls.getCOUNT() == 0) {
		System.out.println("[Inactive Failed]");
		msg = "유저 정보가 없습니다.";
		redirectURL = "../view/myInfo.jsp";
	} else {
		System.out.println("[Inactive Succeed]");
		msg = "";
		
		CM_USERINF clsCM_USERINF = new CM_USERINF();
		
		clsCM_USERINF.setUSER_ID(cls.getUSER_ID());
		clsCM_USERINF.setUSER_PW(cls.getUSER_PW());
		clsCM_USERINF.setUSER_NM(cls.getUSER_NM());
		clsCM_USERINF.setUSER_HP(cls.getUSER_HP());
		clsCM_USERINF.setGRP_ID(cls.getGRP_ID());
		clsCM_USERINF.setINACTIVE_YN("Y");
		clsCM_USERINF.setINACTIVE_DATE(stringUtil.dtToStrYMD(now));
		clsCM_USERINF.setREMARK(cls.getREMARK());
		clsCM_USERINF.setLAST_CHNG_USER(strUSER_ID);
		clsCM_USERINF.setLAST_CHNG_DT(stringUtil.dtToStrYMDHMS(now));

		dao.updateRecord(clsCM_USERINF);
		
		msg = "회원탈퇴되었습니다.";
		redirectURL = "../view/login.jsp";
		
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