package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CLS_WAITING;
import Util.DBConnection;

public class daoCLS_WAITING_Q {

	// 조회 (단건)
	public CLS_WAITING findRecordByUser(CLS_WAITING clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CLS_WAITING cls = new CLS_WAITING();

		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CLS_WAITING														" + "\n");
			strSQL.append("WHERE	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			strSQL.append("AND		CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			strSQL.append("AND		RESV_USER = '" + clsInput.getRESV_USER() + "'					" + "\n");
			strSQL.append("AND		CNCL_YN = 'N'													" + "\n");
			strSQL.append("LIMIT	1																" + "\n");
			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("F", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cls = new CLS_WAITING();

				cls.setWAIT_DATE(rs.getString("WAIT_DATE"));
				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setWAIT_NO(rs.getInt("WAIT_NO"));
				cls.setRESV_USER(rs.getString("RESV_USER"));
				cls.setRESV_USER_HP(rs.getString("RESV_USER_HP"));
				cls.setRESV_DT(rs.getString("RESV_DT"));
				cls.setUSE_YN(rs.getString("USE_YN"));
				cls.setUSE_DT(rs.getString("USE_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setCNCL_USER(rs.getString("CNCL_USER"));
				cls.setREMARK(rs.getString("REMARK"));
				cls.setLAST_CHNG_USER(rs.getString("LAST_CHNG_USER"));
				cls.setLAST_CHNG_DT(rs.getString("LAST_CHNG_DT"));

				cls.setCOUNT(1);

			}

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

		return cls;
	}

	// 조회 (다건)
	public ArrayList<CLS_WAITING> getRecordByUser(CLS_WAITING clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CLS_WAITING> list = new ArrayList<CLS_WAITING>();
		CLS_WAITING cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT			R.CLASS_NO			AS	CLASS_NO                        		" + "\n");
			strSQL.append("					, R.CLASS_NM        AS	CLASS_NM                         		" + "\n");
			strSQL.append("                	, R.CLASS_ADDR      AS	CLASS_ADDR                       		" + "\n");
			strSQL.append("					, R.CLASS_DATE      AS	CLASS_DATE                         		" + "\n");
			strSQL.append("                	, R.CLASS_TIME      AS	CLASS_TIME                       		" + "\n");
			strSQL.append("                	, R.OPEN_USER       AS	OPEN_USER                       		" + "\n");
			strSQL.append("                	, R.OPEN_DATE       AS	OPEN_DATE                       		" + "\n");
			strSQL.append("                	, R.CLOSE_DATE      AS	CLOSE_DATE                      		" + "\n");
			strSQL.append("                	, R.CALL_YN         AS	CALL_YN                         		" + "\n");
			strSQL.append("                	, R.CALL_TYPE       AS	CALL_TYPE                       		" + "\n");
			strSQL.append("                	, R.REMARK          AS	RMK                          			" + "\n");
			strSQL.append("                	, W.WAIT_DATE       AS	WAIT_DATE                       		" + "\n");
			strSQL.append("                	, W.WAIT_NO         AS	WAIT_NO                         		" + "\n");
			strSQL.append("                	, W.RESV_USER       AS	RESV_USER                       		" + "\n");
			strSQL.append("                	, W.RESV_USER_HP    AS	RESV_USER_HP                    		" + "\n");
			strSQL.append("                	, W.RESV_DT         AS	RESV_DT                         		" + "\n");
			strSQL.append("                	, W.USE_YN          AS	USE_YN                          		" + "\n");
			strSQL.append("                	, W.USE_DT          AS	USE_DT                          		" + "\n");
			strSQL.append("                	, W.CNCL_YN         AS	CNCL_YN                         		" + "\n");
			strSQL.append("                	, W.CNCL_DT         AS	CNCL_DT                         		" + "\n");
			strSQL.append("                	, W.REMARK			AS	REMARK									" + "\n");
			strSQL.append("					, C1.CMCD_NM		AS	CLASS_TYPE_NM							" + "\n");
			strSQL.append("					, C2.CMCD_NM		AS	CLASS_DATE_NM							" + "\n");
			strSQL.append("					, C3.CMCD_NM		AS	CLASS_TIME_NM							" + "\n");
			strSQL.append("FROM				CLS_CLASS	AS	R												" + "\n");
			strSQL.append("INNER JOIN		CLS_WAITING	AS	W	ON	W.CLASS_NO = R.CLASS_NO					" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C1	ON	C1.CMCD_VAL = R.CLASS_TYPE				" + "\n");
			strSQL.append("										AND	C1.CMCD_ID = 'CLASS_TYPE'				" + "\n");
			strSQL.append("										AND	C1.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C2	ON	C2.CMCD_VAL = R.CLASS_DATE				" + "\n");
			strSQL.append("										AND	C2.CMCD_ID = 'CLASS_DATE'				" + "\n");
			strSQL.append("										AND	C2.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C3	ON	C3.CMCD_VAL = R.CLASS_TIME				" + "\n");
			strSQL.append("										AND	C3.CMCD_ID = 'CLASS_TIME'				" + "\n");
			strSQL.append("										AND	C3.USE_YN = 'Y'							" + "\n");
			strSQL.append("WHERE	1 = 1																	" + "\n");

			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	W.CLASS_NO = '" + clsInput.getCLASS_NO() + "'								" + "\n");
			}

			if (clsInput.getOPEN_USER() != null && clsInput.getOPEN_USER().equals("") == false) {
				strSQL.append("AND	R.OPEN_USER = '" + clsInput.getOPEN_USER() + "'							" + "\n");
			}

			if (clsInput.getRESV_USER() != null && clsInput.getRESV_USER().equals("") == false) {
				strSQL.append("AND	W.RESV_USER = '" + clsInput.getRESV_USER() + "'							" + "\n");
			}

			if (clsInput.getCNCL_YN() != null) {
				if (clsInput.getCNCL_YN().equals("Y")) {
					strSQL.append("AND	W.CNCL_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.CNCL_YN IN ('', 'N')												" + "\n");
				}
			}

			if (clsInput.getUSE_YN() != null) {
				if (clsInput.getUSE_YN().equals("Y")) {
					strSQL.append("AND	W.USE_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.USE_YN IN ('', 'N')												" + "\n");
				}
			}

			strSQL.append(";");

			System.out.println(strSQL.toString());

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CLS_WAITING();

				cls.setCLASS_NM(rs.getString("CLASS_NM"));
				cls.setCLASS_ADDR(rs.getString("CLASS_ADDR"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_DATE_NM(rs.getString("CLASS_DATE_NM"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setCLASS_TIME_NM(rs.getString("CLASS_TIME_NM"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));

				cls.setWAIT_DATE(rs.getString("WAIT_DATE"));
				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setWAIT_NO(rs.getInt("WAIT_NO"));
				cls.setRESV_USER(rs.getString("RESV_USER"));
				cls.setRESV_USER_HP(rs.getString("RESV_USER_HP"));
				cls.setRESV_DT(rs.getString("RESV_DT"));
				cls.setUSE_YN(rs.getString("USE_YN"));
				cls.setUSE_DT(rs.getString("USE_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setREMARK(rs.getString("REMARK"));

				cls.setCOUNT(cnt);

				list.add(cls);
			}

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

		return list;
	}

	// 조회 (다건)s
	public CLS_WAITING findRecordByRoom(CLS_WAITING clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CLS_WAITING cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT			R.CLASS_NO			AS	CLASS_NO                        		" + "\n");
			strSQL.append("					, R.CLASS_NM        AS	CLASS_NM                         		" + "\n");
			strSQL.append("                	, R.CLASS_ADDR      AS	CLASS_ADDR                       		" + "\n");
			strSQL.append("					, R.CLASS_DATE      AS	CLASS_DATE                         		" + "\n");
			strSQL.append("                	, R.CLASS_TIME      AS	CLASS_TIME                       		" + "\n");
			strSQL.append("                	, R.OPEN_USER       AS	OPEN_USER                       		" + "\n");
			strSQL.append("                	, R.OPEN_DATE       AS	OPEN_DATE                       		" + "\n");
			strSQL.append("                	, R.CLOSE_DATE      AS	CLOSE_DATE                      		" + "\n");
			strSQL.append("                	, R.REMARK          AS	RMK                          			" + "\n");
			strSQL.append("                	, W.WAIT_DATE       AS	WAIT_DATE                       		" + "\n");
			strSQL.append("                	, W.WAIT_NO         AS	WAIT_NO                         		" + "\n");
			strSQL.append("                	, W.RESV_USER       AS	RESV_USER                       		" + "\n");
			strSQL.append("                	, W.RESV_USER_HP    AS	RESV_USER_HP                    		" + "\n");
			strSQL.append("                	, W.RESV_DT         AS	RESV_DT                         		" + "\n");
			strSQL.append("                	, W.USE_YN          AS	USE_YN                          		" + "\n");
			strSQL.append("                	, W.USE_DT          AS	USE_DT                          		" + "\n");
			strSQL.append("                	, W.CNCL_YN         AS	CNCL_YN                         		" + "\n");
			strSQL.append("                	, W.CNCL_DT         AS	CNCL_DT                         		" + "\n");
			strSQL.append("                	, W.REMARK			AS	REMARK									" + "\n");
			strSQL.append("					, C1.CMCD_NM		AS	CLASS_TYPE_NM							" + "\n");
			strSQL.append("					, C2.CMCD_NM		AS	CLASS_DATE_NM							" + "\n");
			strSQL.append("					, C3.CMCD_NM		AS	CLASS_TIME_NM							" + "\n");
			strSQL.append("FROM				CLS_CLASSINF		AS	R										" + "\n");
			strSQL.append("INNER JOIN		CLS_WAITING	AS	W	ON	W.CLASS_NO = R.CLASS_NO					" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C1	ON	C1.CMCD_VAL = R.RCLASS_TYPE				" + "\n");
			strSQL.append("										AND	C1.CMCD_ID = 'CLASS_TYPE'				" + "\n");
			strSQL.append("										AND	C1.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C2	ON	C2.CMCD_VAL = R.CLASS_DATE				" + "\n");
			strSQL.append("										AND	C2.CMCD_ID = 'CLASS_DATE'				" + "\n");
			strSQL.append("										AND	C2.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C3	ON	C3.CMCD_VAL = R.CLASS_TIME				" + "\n");
			strSQL.append("										AND	C3.CMCD_ID = 'CLASS_TIME'				" + "\n");
			strSQL.append("										AND	C3.USE_YN = 'Y'							" + "\n");
			strSQL.append("WHERE	1 = 1																	" + "\n");

			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	W.CLASS_NO = '" + clsInput.getCLASS_NO() + "'								" + "\n");
			}

			if (clsInput.getOPEN_USER() != null && clsInput.getOPEN_USER().equals("") == false) {
				strSQL.append("AND	R.OPEN_USER = '" + clsInput.getOPEN_USER() + "'							" + "\n");
			}

			if (clsInput.getRESV_USER() != null && clsInput.getRESV_USER().equals("") == false) {
				strSQL.append("AND	W.RESV_USER = '" + clsInput.getRESV_USER() + "'							" + "\n");
			}

			if (clsInput.getCNCL_YN() != null) {
				if (clsInput.getCNCL_YN().equals("Y")) {
					strSQL.append("AND	W.CNCL_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.CNCL_YN IN ('', 'N')												" + "\n");
				}
			}

			if (clsInput.getUSE_YN() != null) {
				if (clsInput.getUSE_YN().equals("Y")) {
					strSQL.append("AND	W.USE_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.USE_YN IN ('', 'N')												" + "\n");
				}
			}

			strSQL.append("LIMIT	1																		" + "\n");
			strSQL.append(";");

			System.out.println(strSQL.toString());

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CLS_WAITING();

				cls.setCLASS_NM(rs.getString("CLASS_NM"));
				cls.setCLASS_ADDR(rs.getString("RCLASS_ADDR"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_DATE_NM(rs.getString("CLASS_DATE_NM"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setCLASS_TIME_NM(rs.getString("CLASS_TIME_NM"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));

				cls.setWAIT_DATE(rs.getString("WAIT_DATE"));
				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setWAIT_NO(rs.getInt("WAIT_NO"));
				cls.setRESV_USER(rs.getString("RESV_USER"));
				cls.setRESV_USER_HP(rs.getString("RESV_USER_HP"));
				cls.setRESV_DT(rs.getString("RESV_DT"));
				cls.setUSE_YN(rs.getString("USE_YN"));
				cls.setUSE_DT(rs.getString("USE_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setREMARK(rs.getString("REMARK"));

				cls.setCOUNT(cnt);

			}

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

		return cls;
	}

	// 조회 (다건)
	public ArrayList<CLS_WAITING> getRecordByRoom(CLS_WAITING clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CLS_WAITING> list = new ArrayList<CLS_WAITING>();
		CLS_WAITING cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT			R.CLASS_NO			AS	CLASS_NO                        			" + "\n");
			strSQL.append("					, R.CLASS_NM        AS	CLASS_NM                         		" + "\n");
			strSQL.append("                	, R.CLASS_ADDR      AS	CLASS_ADDR                       		" + "\n");
			strSQL.append("					, R.CLASS_DATE      AS	CLASS_DATE                         		" + "\n");
			strSQL.append("                	, R.CLASS_TIME      AS	CLASS_TIME                       		" + "\n");
			strSQL.append("                	, R.OPEN_USER       AS	OPEN_USER                       		" + "\n");
			strSQL.append("                	, R.OPEN_DATE       AS	OPEN_DATE                       		" + "\n");
			strSQL.append("                	, R.CLOSE_DATE      AS	CLOSE_DATE                      		" + "\n");
			strSQL.append("                	, R.REMARK          AS	RMK                          			" + "\n");
			strSQL.append("                	, W.WAIT_DATE       AS	WAIT_DATE                       		" + "\n");
			strSQL.append("                	, W.WAIT_NO         AS	WAIT_NO                         		" + "\n");
			strSQL.append("                	, W.RESV_USER       AS	RESV_USER                       		" + "\n");
			strSQL.append("                	, W.RESV_USER_HP    AS	RESV_USER_HP                    		" + "\n");
			strSQL.append("                	, W.RESV_DT         AS	RESV_DT                         		" + "\n");
			strSQL.append("                	, W.USE_YN          AS	USE_YN                          		" + "\n");
			strSQL.append("                	, W.USE_DT          AS	USE_DT                          		" + "\n");
			strSQL.append("                	, W.CNCL_YN         AS	CNCL_YN                         		" + "\n");
			strSQL.append("                	, W.CNCL_DT         AS	CNCL_DT                         		" + "\n");
			strSQL.append("                	, W.REMARK			AS	REMARK									" + "\n");
			strSQL.append("					, C1.CMCD_NM		AS	CLASS_TYPE_NM							" + "\n");
			strSQL.append("					, C2.CMCD_NM		AS	CLASS_DATE_NM							" + "\n");
			strSQL.append("					, C3.CMCD_NM		AS	CLASS_TIME_NM							" + "\n");
			strSQL.append("					, C5.CMCD_NM		AS	CALL_TYPE_NM							" + "\n");
			strSQL.append("FROM				CLS_CLASSINF		AS	R										" + "\n");
			strSQL.append("INNER JOIN		CLS_WAITING	AS	W	ON	W.CLASS_NO = R.CLASS_NO					" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C1	ON	C1.CMCD_VAL = R.CLASS_TYPE				" + "\n");
			strSQL.append("										AND	C1.CMCD_ID = 'CLASS_TYPE'				" + "\n");
			strSQL.append("										AND	C1.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C2	ON	C2.CMCD_VAL = R.CLASS_DATE				" + "\n");
			strSQL.append("										AND	C2.CMCD_ID = 'CLASS_DATE'				" + "\n");
			strSQL.append("										AND	C2.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C3	ON	C3.CMCD_VAL = R.CLASS_TIME				" + "\n");
			strSQL.append("										AND	C3.CMCD_ID = 'CLASS_TIME'				" + "\n");
			strSQL.append("										AND	C3.USE_YN = 'Y'							" + "\n");
			strSQL.append("WHERE	1 = 1																	" + "\n");

			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	W.CLASS_NO = '" + clsInput.getCLASS_NO() + "'							"
						+ "" + "\n");
			}

			if (clsInput.getOPEN_USER() != null && clsInput.getOPEN_USER().equals("") == false) {
				strSQL.append("AND	R.OPEN_USER = '" + clsInput.getOPEN_USER() + "'							" + "\n");
			}

			if (clsInput.getRESV_USER() != null && clsInput.getRESV_USER().equals("") == false) {
				strSQL.append("AND	W.RESV_USER = '" + clsInput.getRESV_USER() + "'							" + "\n");
			}

			if (clsInput.getCNCL_YN() != null) {
				if (clsInput.getCNCL_YN().equals("Y")) {
					strSQL.append("AND	W.CNCL_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.CNCL_YN IN ('', 'N')												" + "\n");
				}
			}

			if (clsInput.getUSE_YN() != null) {
				if (clsInput.getUSE_YN().equals("Y")) {
					strSQL.append("AND	W.USE_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	W.USE_YN IN ('', 'N')												" + "\n");
				}
			}

			strSQL.append("ORDER BY	W.WAIT_DATE																" + "\n");
			strSQL.append(";");

			System.out.println(strSQL.toString());

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CLS_WAITING();

				cls.setCLASS_NM(rs.getString("CLASS_NM"));
				cls.setCLASS_ADDR(rs.getString("CLASS_ADDR"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_DATE_NM(rs.getString("CLASS_DATE_NM"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setCLASS_TIME_NM(rs.getString("CLASS_TIME_NM"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));

				cls.setWAIT_DATE(rs.getString("WAIT_DATE"));
				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setWAIT_NO(rs.getInt("WAIT_NO"));
				cls.setRESV_USER(rs.getString("RESV_USER"));
				cls.setRESV_USER_HP(rs.getString("RESV_USER_HP"));
				cls.setRESV_DT(rs.getString("RESV_DT"));
				cls.setUSE_YN(rs.getString("USE_YN"));
				cls.setUSE_DT(rs.getString("USE_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setREMARK(rs.getString("REMARK"));

				cls.setCOUNT(cnt);

				list.add(cls);
			}

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

		return list;
	}
}
