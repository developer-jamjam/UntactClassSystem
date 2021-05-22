package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CM_SEQNO;
import Util.DBConnection;

public class daoCM_SEQNO {

	public void insertRecord(CM_SEQNO clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("INSERT INTO CM_SEQNO													" + "\n");
			strSQL.append("VALUES (	 '" + clsInput.getSEQ_ID() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getSEQ_YEAR() + "'							" + "\n");
			strSQL.append("			,'" + clsInput.getSEQ_MONTH() + "'							" + "\n");
			strSQL.append("			,'" + clsInput.getSEQ_NO() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getLAST_CHNG_USER() + "'						" + "\n");
			strSQL.append("			,'" + clsInput.getLAST_CHNG_DT() + "'						" + "\n");
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
	public void updateRecord(CM_SEQNO clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("UPDATE	CM_SEQNO													" + "\n");
			strSQL.append("SET		SEQ_NO = '" + clsInput.getSEQ_NO() + "'						" + "\n");
			strSQL.append("			, LAST_CHNG_USER = '" + clsInput.getLAST_CHNG_USER() + "'	" + "\n");
			strSQL.append("			, LAST_CHNG_DT = '" + clsInput.getLAST_CHNG_DT() + "'		" + "\n");
			strSQL.append("WHERE	SEQ_ID = '" + clsInput.getSEQ_ID() + "'						" + "\n");
			strSQL.append("AND		SEQ_YEAR = '" + clsInput.getSEQ_YEAR() + "'					" + "\n");
			strSQL.append("AND		SEQ_MONTH = '" + clsInput.getSEQ_MONTH() + "'				" + "\n");
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
	public void deleteRecord(CM_SEQNO clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("DELETE																" + "\n");
			strSQL.append("FROM		CM_SEQNO													" + "\n");
			strSQL.append("WHERE	SEQ_ID = '" + clsInput.getSEQ_ID() + "'						" + "\n");
			strSQL.append("AND		SEQ_YEAR = '" + clsInput.getSEQ_YEAR() + "'					" + "\n");
			strSQL.append("AND		SEQ_MONTH = '" + clsInput.getSEQ_MONTH() + "'				" + "\n");
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
	public CM_SEQNO findRecord(CM_SEQNO clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CM_SEQNO cls = new CM_SEQNO();
		
		try {

			// SQL
			strSQL.append("SELECT	*															" + "\n");
			strSQL.append("FROM		CM_SEQNO													" + "\n");
			strSQL.append("WHERE	SEQ_ID = '" + clsInput.getSEQ_ID() + "'						" + "\n");
			strSQL.append("AND		SEQ_YEAR = '" + clsInput.getSEQ_YEAR() + "'					" + "\n");
			strSQL.append("AND		SEQ_MONTH = '" + clsInput.getSEQ_MONTH() + "'				" + "\n");
			strSQL.append("LIMIT	1															" + "\n");
			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("F", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cls = new CM_SEQNO();

				cls.setSEQ_ID(rs.getString("SEQ_ID"));
				cls.setSEQ_YEAR(rs.getString("SEQ_YEAR"));
				cls.setSEQ_MONTH(rs.getString("SEQ_MONTH"));
				cls.setSEQ_NO(rs.getInt("SEQ_NO"));
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
	public ArrayList<CM_SEQNO> getRecord(CM_SEQNO clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CM_SEQNO> list = new ArrayList<CM_SEQNO>();
		CM_SEQNO cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT	*															" + "\n");
			strSQL.append("FROM		CM_SEQNO													" + "\n");
			strSQL.append("WHERE	1 = 1														" + "\n");

			if (clsInput.getSEQ_ID() != null && clsInput.getSEQ_ID().equals("") == false) {
				strSQL.append("AND	SEQ_ID = '" + clsInput.getSEQ_ID() + "'						" + "\n");
			}
			
			if (clsInput.getSEQ_YEAR() != null && clsInput.getSEQ_YEAR().equals("") == false) {
				strSQL.append("AND	SEQ_YEAR = '" + clsInput.getSEQ_YEAR() + "'					" + "\n");
			}
			
			if (clsInput.getSEQ_MONTH() != null && clsInput.getSEQ_MONTH().equals("") == false) {
				strSQL.append("AND	SEQ_MONTH = '" + clsInput.getSEQ_MONTH() + "'				" + "\n");
			}

			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CM_SEQNO();

				cls.setSEQ_ID(rs.getString("SEQ_ID"));
				cls.setSEQ_YEAR(rs.getString("SEQ_YEAR"));
				cls.setSEQ_MONTH(rs.getString("SEQ_MONTH"));
				cls.setSEQ_NO(rs.getInt("SEQ_NO"));
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
