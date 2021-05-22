package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CLS_WAITING;
import Util.DBConnection;

public class daoCLS_WAITING {

	public void insertRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		StringBuilder strSQL = new StringBuilder();

		try {

			// SQL
			strSQL.append("INSERT INTO CLS_WAITING													" + "\n");
			strSQL.append("VALUES (	  '" + clsInput.getWAIT_DATE() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getCLASS_NO() + "'								" + "\n");
			strSQL.append("			, " + clsInput.getWAIT_NO() + "									" + "\n");
			strSQL.append("			, '" + clsInput.getRESV_USER() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getRESV_USER_HP() + "'							" + "\n");
			strSQL.append("			, '" + clsInput.getRESV_DT() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getUSE_YN() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getUSE_DT() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getCNCL_YN() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getCNCL_DT() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getCNCL_USER() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getREMARK() + "'								" + "\n");
			strSQL.append("			, '" + clsInput.getLAST_CHNG_USER() + "'						" + "\n");
			strSQL.append("			, '" + clsInput.getLAST_CHNG_DT() + "'							" + "\n");
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
	public void updateRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		StringBuilder strSQL = new StringBuilder();

		try {

			// SQL
			strSQL.append("UPDATE	CLS_WAITING														" + "\n");
			strSQL.append("SET		  RESV_USER         = '" + clsInput.getRESV_USER() + "'			" + "\n");
			strSQL.append("			, RESV_USER_HP		= '" + clsInput.getRESV_USER_HP() + "'		" + "\n");
			strSQL.append("			, RESV_DT           = '" + clsInput.getRESV_DT() + "'			" + "\n");
			strSQL.append("			, USE_YN            = '" + clsInput.getUSE_YN() + "'			" + "\n");
			strSQL.append("			, USE_DT            = '" + clsInput.getUSE_DT() + "'			" + "\n");
			strSQL.append("			, CNCL_YN           = '" + clsInput.getCNCL_YN() + "'			" + "\n");
			strSQL.append("			, CNCL_DT           = '" + clsInput.getCNCL_DT() + "'			" + "\n");
			strSQL.append("			, CNCL_USER         = '" + clsInput.getCNCL_USER() + "'			" + "\n");
			strSQL.append("			, REMARK            = '" + clsInput.getREMARK() + "'			" + "\n");
			strSQL.append("			, LAST_CHNG_USER	= '" + clsInput.getLAST_CHNG_USER() + "'	" + "\n");
			strSQL.append("			, LAST_CHNG_DT      = '" + clsInput.getLAST_CHNG_DT() + "'		" + "\n");
			strSQL.append("WHERE	WAIT_DATE			= '" + clsInput.getWAIT_DATE() + "'			" + "\n");
			strSQL.append("AND		CLASS_NO				= '" + clsInput.getCLASS_NO() + "'			" + "\n");
			strSQL.append("AND		WAIT_NO				= " + clsInput.getWAIT_NO() + "				" + "\n");
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
	public void deleteRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		StringBuilder strSQL = new StringBuilder();

		try {

			// SQL
			strSQL.append("DELETE																	" + "\n");
			strSQL.append("FROM		CLS_WAITING														" + "\n");
			strSQL.append("WHERE	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			strSQL.append("AND		CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			strSQL.append("AND		WAIT_NO = " + clsInput.getWAIT_NO() + "							" + "\n");
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
	public CLS_WAITING findRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CLS_WAITING cls = new CLS_WAITING();

		StringBuilder strSQL = new StringBuilder();

		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CLS_WAITING														" + "\n");
			strSQL.append("WHERE	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			strSQL.append("AND		CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			strSQL.append("AND		WAIT_NO = " + clsInput.getWAIT_NO() + "							" + "\n");
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
	public ArrayList<CLS_WAITING> getRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CLS_WAITING> list = new ArrayList<CLS_WAITING>();
		CLS_WAITING cls = null;

		StringBuilder strSQL = new StringBuilder();
		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CLS_WAITING														" + "\n");
			strSQL.append("WHERE	1 = 1															" + "\n");

			if (clsInput.getWAIT_DATE() != null && clsInput.getWAIT_DATE().equals("") == false) {
				strSQL.append("AND	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			}
			
			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			}
			
			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	WAIT_DATE = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			}
			
			strSQL.append("WHERE	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			strSQL.append("AND		CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			strSQL.append("AND		WAIT_NO = " + clsInput.getWAIT_NO() + "							" + "\n");

			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

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

	//
	// 조회 (단건)
	public CLS_WAITING findLastRecord(CLS_WAITING clsInput) {

		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CLS_WAITING cls = new CLS_WAITING();

		StringBuilder strSQL = new StringBuilder();

		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CLS_WAITING														" + "\n");
			strSQL.append("WHERE	WAIT_DATE = '" + clsInput.getWAIT_DATE() + "'					" + "\n");
			strSQL.append("AND		CLASS_NO = '" + clsInput.getCLASS_NO() + "'						" + "\n");
			strSQL.append("ORDER BY	WAIT_NO DESC													" + "\n");
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

}
