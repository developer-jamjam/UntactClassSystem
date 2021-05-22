package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CLS_CLASSINF;
import Util.DBConnection;

public class daoCLS_CLASSINF {
	public void insertRecord(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("INSERT INTO CLS_CLASSINF													" + "\n");
			strSQL.append("VALUES (	 '" + clsInput.getCLASS_NO() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLASS_NM() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLASS_ADDR() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLASS_TYPE() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLASS_DATE() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLASS_TIME() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getOPEN_USER() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getOPEN_DATE() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCLOSE_DATE() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCALL_YN() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCALL_TYPE() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getAPPLY_DT() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCNCL_YN() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCNCL_DT() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getCNCL_USER() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getAPPROVE_YN() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getAPPROVE_DT() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getAPPROVE_USER() + "'							" + "\n");
			strSQL.append("			,'" + clsInput.getREMARK() + "'									" + "\n");
			strSQL.append("			,'" + clsInput.getLAST_CHNG_USER() + "'							" + "\n");
			strSQL.append("			,'" + clsInput.getLAST_CHNG_DT() + "'							" + "\n");
			strSQL.append(");");

			// SQL 실행
			rs = dbCon.SQLExecute("I", strSQL.toString());

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

	}

	// 수정
	public void updateRecord(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("UPDATE	CLS_CLASSINF															" + "\n");
			strSQL.append("SET		CLASS_NM = '" + clsInput.getCLASS_NM() + "'							" + "\n");
			strSQL.append("			, CLASS_ADDR = '" + clsInput.getCLASS_ADDR() + "'						" + "\n");
			strSQL.append("			, CLASS_TYPE = '" + clsInput.getCLASS_TYPE() + "'						" + "\n");
			strSQL.append("			, CLASS_DATE = '" + clsInput.getCLASS_DATE() + "'						" + "\n");
			strSQL.append("			, CLASS_TIME = '" + clsInput.getCLASS_TIME() + "'						" + "\n");
			strSQL.append("			, OPEN_USER = '" + clsInput.getOPEN_USER() + "'						" + "\n");
			strSQL.append("			, OPEN_DATE = '" + clsInput.getOPEN_DATE() + "'						" + "\n");
			strSQL.append("			, CLOSE_DATE = '" + clsInput.getCLOSE_DATE() + "'					" + "\n");
			strSQL.append("			, CALL_YN = '" + clsInput.getCALL_YN() + "'							" + "\n");
			strSQL.append("			, CALL_TYPE = '" + clsInput.getCALL_TYPE() + "'						" + "\n");
			strSQL.append("			, APPLY_DT = '" + clsInput.getAPPLY_DT() + "'						" + "\n");
			strSQL.append("			, CNCL_YN = '" + clsInput.getCNCL_YN() + "'							" + "\n");
			strSQL.append("			, CNCL_DT = '" + clsInput.getCNCL_DT() + "'							" + "\n");
			strSQL.append("			, CNCL_USER = '" + clsInput.getCNCL_USER() + "'						" + "\n");
			strSQL.append("			, APPROVE_YN = '" + clsInput.getAPPROVE_YN() + "'					" + "\n");
			strSQL.append("			, APPROVE_DT = '" + clsInput.getAPPROVE_DT() + "'					" + "\n");
			strSQL.append("			, APPROVE_USER = '" + clsInput.getAPPROVE_USER() + "'				" + "\n");
			strSQL.append("			, REMARK = '" + clsInput.getREMARK() + "'							" + "\n");
			strSQL.append("			, LAST_CHNG_USER = '" + clsInput.getLAST_CHNG_USER() + "'			" + "\n");
			strSQL.append("			, LAST_CHNG_DT = '" + clsInput.getLAST_CHNG_DT() + "'				" + "\n");
			strSQL.append("WHERE	CLASS_NO = '" + clsInput.getCLASS_NO() + "'							" + "\n");
			strSQL.append(";");
			

			// SQL 실행
			rs = dbCon.SQLExecute("U", strSQL.toString());

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

	}

	// 삭제
	public void deleteRecord(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("DELETE																		" + "\n");
			strSQL.append("FROM		CLS_CLASSINF														" + "\n");
			strSQL.append("WHERE	CLASS_NO = '" + clsInput.getCLASS_NO() + "'							" + "\n");
			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("D", strSQL.toString());

			// DBConnectionClose
			dbCon.DBConnectionClose();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

	}

	// 조회 (단건)
	public CLS_CLASSINF findRecord(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CLS_CLASSINF cls = new CLS_CLASSINF();

		try {

			// SQL
			strSQL.append("SELECT	*																	" + "\n");
			strSQL.append("FROM		CLS_CLASSINF															" + "\n");
			strSQL.append("WHERE	CLASS_NO = '" + clsInput.getCLASS_NO() + "'							" + "\n");
			strSQL.append("LIMIT	1																	" + "\n");
			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("F", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cls = new CLS_CLASSINF();

				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setCLASS_NM(rs.getString("CLASS_NM"));
				cls.setCLASS_ADDR(rs.getString("CLASS_ADDR"));
				cls.setCLASS_TYPE(rs.getString("CLASS_TYPE"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));
				cls.setCALL_YN(rs.getString("CALL_YN"));
				cls.setCALL_TYPE(rs.getString("CALL_TYPE"));
				cls.setAPPLY_DT(rs.getString("APPLY_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setCNCL_USER(rs.getString("CNCL_USER"));
				cls.setAPPROVE_YN(rs.getString("APPROVE_YN"));
				cls.setAPPROVE_DT(rs.getString("APPROVE_DT"));
				cls.setAPPROVE_USER(rs.getString("APPROVE_USER"));
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
	public ArrayList<CLS_CLASSINF> getRecord(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CLS_CLASSINF> list = new ArrayList<CLS_CLASSINF>();
		CLS_CLASSINF cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT	*																	" + "\n");
			strSQL.append("FROM		CLS_CLASSINF														" + "\n");
			strSQL.append("WHERE	1 = 1																" + "\n");

			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	CLASS_NO = '" + clsInput.getCLASS_NO() + "'							" + "\n");
			}

			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CLS_CLASSINF();

				cls.setCLASS_NO(rs.getString("CLASS_NO"));
				cls.setCLASS_NM(rs.getString("CLASS_NM"));
				cls.setCLASS_ADDR(rs.getString("CLASS_ADDR"));
				cls.setCLASS_TYPE(rs.getString("CLASS_TYPE"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));
				cls.setCALL_YN(rs.getString("CALL_YN"));
				cls.setCALL_TYPE(rs.getString("CALL_TYPE"));
				cls.setAPPLY_DT(rs.getString("APPLY_DT"));
				cls.setCNCL_YN(rs.getString("CNCL_YN"));
				cls.setCNCL_DT(rs.getString("CNCL_DT"));
				cls.setCNCL_USER(rs.getString("CNCL_USER"));
				cls.setAPPROVE_YN(rs.getString("APPROVE_YN"));
				cls.setAPPROVE_DT(rs.getString("APPROVE_DT"));
				cls.setAPPROVE_USER(rs.getString("APPROVE_USER"));
				cls.setREMARK(rs.getString("REMARK"));
				cls.setLAST_CHNG_USER(rs.getString("LAST_CHNG_USER"));
				cls.setLAST_CHNG_DT(rs.getString("LAST_CHNG_DT"));

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
