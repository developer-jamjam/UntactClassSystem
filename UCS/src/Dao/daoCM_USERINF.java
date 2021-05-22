package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Util.DBConnection;
import Domain.CM_USERINF;

public class daoCM_USERINF {

	public void insertRecord(CM_USERINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("INSERT INTO CM_USERINF													" + "\n");
			strSQL.append("VALUES (	 '" + clsInput.getUSER_ID() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getUSER_PW() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getUSER_NM() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getUSER_HP() + "'								" + "\n");
			strSQL.append("			,'" + clsInput.getGRP_ID() + "'									" + "\n");
			strSQL.append("			,'" + clsInput.getINACTIVE_YN() + "'							" + "\n");
			strSQL.append("			,'" + clsInput.getINACTIVE_DATE() + "'							" + "\n");
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
	public void updateRecord(CM_USERINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("UPDATE	CM_USERINF														" + "\n");
			strSQL.append("SET		USER_PW = '" + clsInput.getUSER_PW() + "'						" + "\n");
			strSQL.append("			, USER_NM = '" + clsInput.getUSER_NM() + "'						" + "\n");
			strSQL.append("			, USER_HP = '" + clsInput.getUSER_HP() + "'						" + "\n");
			strSQL.append("			, GRP_ID = '" + clsInput.getGRP_ID() + "'						" + "\n");
			strSQL.append("			, INACTIVE_YN = '" + clsInput.getINACTIVE_YN() + "'				" + "\n");
			strSQL.append("			, INACTIVE_DATE = '" + clsInput.getINACTIVE_DATE() + "'			" + "\n");
			strSQL.append("			, REMARK = '" + clsInput.getREMARK() + "'						" + "\n");
			strSQL.append("			, LAST_CHNG_USER = '" + clsInput.getLAST_CHNG_USER() + "'		" + "\n");
			strSQL.append("			, LAST_CHNG_DT = '" + clsInput.getLAST_CHNG_DT() + "'			" + "\n");
			strSQL.append("WHERE	USER_ID = '" + clsInput.getUSER_ID() + "'						" + "\n");
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
	public void deleteRecord(CM_USERINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		try {

			// SQL
			strSQL.append("DELETE																	" + "\n");
			strSQL.append("FROM		CM_USERINF														" + "\n");
			strSQL.append("WHERE	USER_ID = '" + clsInput.getUSER_ID() + "'						" + "\n");
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
	public CM_USERINF findRecord(CM_USERINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		CM_USERINF cls = new CM_USERINF();
		
		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CM_USERINF														" + "\n");
			strSQL.append("WHERE	USER_ID = '" + clsInput.getUSER_ID() + "'						" + "\n");
			strSQL.append("LIMIT	1																" + "\n");
			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("F", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cls = new CM_USERINF();

				cls.setUSER_ID(rs.getString("USER_ID"));
				cls.setUSER_PW(rs.getString("USER_PW"));
				cls.setUSER_NM(rs.getString("USER_NM"));
				cls.setUSER_HP(rs.getString("USER_HP"));
				cls.setGRP_ID(rs.getString("GRP_ID"));
				cls.setINACTIVE_YN(rs.getString("INACTIVE_YN"));
				cls.setINACTIVE_DATE(rs.getString("INACTIVE_DATE"));
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
	public ArrayList<CM_USERINF> getRecord(CM_USERINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CM_USERINF> list = new ArrayList<CM_USERINF>();
		CM_USERINF cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT	*																" + "\n");
			strSQL.append("FROM		CM_USERINF														" + "\n");
			strSQL.append("WHERE	1 = 1															" + "\n");

			if (clsInput.getUSER_ID() != null && clsInput.getUSER_ID().equals("") == false) {
				strSQL.append("AND	USER_ID = '" + clsInput.getUSER_ID() + "'						" + "\n");
			}

			strSQL.append(";");

			// SQL 실행
			rs = dbCon.SQLExecute("G", strSQL.toString());

			// 데이터 셋팅
			while (rs.next()) {

				cnt++;

				cls = new CM_USERINF();

				cls.setUSER_ID(rs.getString("USER_ID"));
				cls.setUSER_PW(rs.getString("USER_PW"));
				cls.setUSER_NM(rs.getString("USER_NM"));
				cls.setUSER_HP(rs.getString("USER_HP"));
				cls.setGRP_ID(rs.getString("GRP_ID"));
				cls.setINACTIVE_YN(rs.getString("INACTIVE_YN"));
				cls.setINACTIVE_DATE(rs.getString("INACTIVE_DATE"));
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
