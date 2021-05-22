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
	
	String strService = "[Insert Class Service]";
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
	String strCLASS_NM = URLDecoder.decode(request.getParameter("CLASS_NM"), "UTF-8");
	String strCLASS_ADDR = URLDecoder.decode(request.getParameter("CLASS_ADDR"), "UTF-8");
	String strCLASS_TYPE = "S"; // 무조건
	String strCLASS_DATE = URLDecoder.decode(request.getParameter("CLASS_DATE"), "UTF-8");
	String strCLASS_TIME = URLDecoder.decode(request.getParameter("CLASS_TIME"), "UTF-8");
	String strOPEN_USER = URLDecoder.decode(request.getParameter("OPEN_USER"), "UTF-8");
	String strOPEN_DATE = URLDecoder.decode(request.getParameter("OPEN_DATE"), "UTF-8");
	String strCLOSE_DATE = URLDecoder.decode(request.getParameter("CLOSE_DATE"), "UTF-8");
	String strCALL_YN = URLDecoder.decode(request.getParameter("CALL_YN"), "UTF-8");
	String strCALL_TYPE = URLDecoder.decode(request.getParameter("CALL_TYPE"), "UTF-8");
	String strAPPLY_DT = URLDecoder.decode(request.getParameter("APPLY_DT"), "UTF-8");
	String strCNCL_YN = URLDecoder.decode(request.getParameter("CNCL_YN"), "UTF-8");
	String strCNCL_DT = URLDecoder.decode(request.getParameter("CNCL_DT"), "UTF-8");
	String strCNCL_USER = URLDecoder.decode(request.getParameter("CNCL_USER"), "UTF-8");
	String strAPPROVE_YN = URLDecoder.decode(request.getParameter("APPROVE_YN"), "UTF-8");
	String strAPPROVE_DT = URLDecoder.decode(request.getParameter("APPROVE_DT"), "UTF-8");
	String strAPPROVE_USER = URLDecoder.decode(request.getParameter("APPROVE_USER"), "UTF-8");
	String strREMARK = URLDecoder.decode(request.getParameter("REMARK"), "UTF-8");
	String strLAST_CHNG_USER = URLDecoder.decode(request.getParameter("LAST_CHNG_USER"), "UTF-8");
	String strLAST_CHNG_DT = URLDecoder.decode(request.getParameter("LAST_CHNG_DT"), "UTF-8");

	System.out.println("CLASS_NO : " + strCLASS_NO);
	System.out.println("CLASS_NM : " + strCLASS_NM);
	System.out.println("CLASS_ADDR : " + strCLASS_ADDR);
	System.out.println("CLASS_TYPE : " + strCLASS_TYPE);
	System.out.println("CLASS_DATE : " + strCLASS_DATE);
	System.out.println("CLASS_TIME : " + strCLASS_TIME);
	System.out.println("OPEN_USER : " + strOPEN_USER);
	System.out.println("OPEN_DATE : " + strOPEN_DATE);
	System.out.println("CLOSE_DATE : " + strCLOSE_DATE);
	System.out.println("CALL_YN : " + strCALL_YN);
	System.out.println("CALL_TYPE : " + strCALL_TYPE);
	System.out.println("APPLY_DT : " + strAPPLY_DT);
	System.out.println("CNCL_YN : " + strCNCL_YN);
	System.out.println("CNCL_DT : " + strCNCL_DT);
	System.out.println("CNCL_USER : " + strCNCL_USER);
	System.out.println("APPROVE_YN : " + strAPPROVE_YN);
	System.out.println("APPROVE_DT : " + strAPPROVE_DT);
	System.out.println("APPROVE_USER : " + strAPPROVE_USER);
	System.out.println("REMARK : " + strREMARK);
	System.out.println("LAST_CHNG_USER : " + strLAST_CHNG_USER);
	System.out.println("LAST_CHNG_DT : " + strLAST_CHNG_DT);


	// 채번
	String strNow = stringUtil.dtToStrYMD(now);

	System.out.println(now.toString());
	System.out.println(strNow);
	SeqUtil seqUtil = new SeqUtil();
	String strSeq = seqUtil.getSeq("CL", strNow, strUSER_ID);

	System.out.println(strSeq);

	// Insert
	daoCLS_CLASSINF dao = new daoCLS_CLASSINF();
	CLS_CLASSINF cls = new CLS_CLASSINF();
	
	// 데이터 셋팅
	cls.setCLASS_NO(strSeq);
	cls.setCLASS_NM(strCLASS_NM);
	cls.setCLASS_ADDR(strCLASS_ADDR);
	cls.setCLASS_TYPE(strCLASS_TYPE);
	cls.setCLASS_DATE(strCLASS_DATE);
	cls.setCLASS_TIME(strCLASS_TIME);
	cls.setOPEN_USER(strUSER_ID);
	cls.setOPEN_DATE(stringUtil.strDateToStrYMD(strOPEN_DATE));
	cls.setCLOSE_DATE(stringUtil.strDateToStrYMD(strCLOSE_DATE));
	cls.setCALL_TYPE(strCALL_TYPE);
	cls.setCALL_YN(strCALL_YN);
	cls.setAPPLY_DT(stringUtil.dtToStrYMDHMS(now));
	cls.setCNCL_YN("N");
	cls.setCNCL_DT("");
	cls.setCNCL_USER("");
	cls.setAPPROVE_YN("N");
	cls.setAPPROVE_DT("");
	cls.setAPPROVE_USER("");
	cls.setREMARK(strREMARK);
	cls.setLAST_CHNG_USER(strUSER_ID);
	cls.setLAST_CHNG_DT(stringUtil.dtToStrYMDHMS(now));

	dao.insertRecord(cls);

	//
	msg = "방 신청되었습니다.";
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

	//}
%>