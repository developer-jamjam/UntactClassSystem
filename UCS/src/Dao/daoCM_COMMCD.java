package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CM_COMMCD;
import Util.DBConnection;

public class daoCM_COMMCD {
	// 1. find
		public void insertRecord(CM_COMMCD inCM_COMMCD) {

			StringBuilder strSQL = new StringBuilder();
			DBConnection dbCon = new DBConnection();
			ResultSet rs = null;

			try {

				// SQL
				strSQL.append("INSERT INTO CM_COMMCD														" + "\n");
				strSQL.append("VALUES (	 '" + inCM_COMMCD.getCMCD_ID() + "'									" + "\n");
				strSQL.append("			,'" + inCM_COMMCD.getCMCD_VAL() + "'								" + "\n");
				strSQL.append("			,'" + inCM_COMMCD.getCMCD_NM() + "'									" + "\n");
				strSQL.append("			,'" + inCM_COMMCD.getUSE_YN() + "'									" + "\n");
				strSQL.append("			,'" + inCM_COMMCD.getLAST_CHNG_USER() + "'							" + "\n");
				strSQL.append("			,'" + inCM_COMMCD.getLAST_CHNG_DT() + "'							" + "\n");
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
		public void updateRecord(CM_COMMCD inCM_COMMCD) {

			StringBuilder strSQL = new StringBuilder();
			DBConnection dbCon = new DBConnection();
			ResultSet rs = null;

			try {

				// SQL
				strSQL.append("UPDATE	CM_COMMCD															" + "\n");
				strSQL.append("SET		CMCD_VAL = '" + inCM_COMMCD.getCMCD_VAL() + "'						" + "\n");
				strSQL.append("			, CMCD_NM = '" + inCM_COMMCD.getCMCD_NM() + "'						" + "\n");
				strSQL.append("			, USE_YN = '" + inCM_COMMCD.getUSE_YN() + "'						" + "\n");
				strSQL.append("			, LAST_CHNG_USER = '" + inCM_COMMCD.getLAST_CHNG_USER() + "'		" + "\n");
				strSQL.append("			, LAST_CHNG_DT = '" + inCM_COMMCD.getLAST_CHNG_DT() + "'			" + "\n");
				strSQL.append("WHERE	ID = '" + inCM_COMMCD.getCMCD_ID() + "'								" + "\n");
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
		public void deleteRecord(CM_COMMCD inCM_COMMCD) {

			StringBuilder strSQL = new StringBuilder();
			DBConnection dbCon = new DBConnection();
			ResultSet rs = null;

			try {

				// SQL
				strSQL.append("DELETE																		" + "\n");
				strSQL.append("FROM		CM_COMMCD															" + "\n");
				strSQL.append("WHERE	CMCD_ID = '" + inCM_COMMCD.getCMCD_ID() + "'						" + "\n");
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
		public CM_COMMCD findRecord(CM_COMMCD inCM_COMMCD) {

			StringBuilder strSQL = new StringBuilder();
			DBConnection dbCon = new DBConnection();
			ResultSet rs = null;

			CM_COMMCD CM_COMMCD = new CM_COMMCD();
			
			try {

				// SQL
				strSQL.append("SELECT	*																	" + "\n");
				strSQL.append("FROM		CM_COMMCD															" + "\n");
				strSQL.append("WHERE	CMCD_ID = '" + inCM_COMMCD.getCMCD_ID() + "'						" + "\n");
				strSQL.append("LIMIT	1																	" + "\n");
				strSQL.append(";");

				// SQL 실행
				rs = dbCon.SQLExecute("F", strSQL.toString());

				// 데이터 셋팅
				while (rs.next()) {

					CM_COMMCD = new CM_COMMCD();
					
					CM_COMMCD.setCMCD_ID(rs.getString("CMCD_ID"));
					CM_COMMCD.setCMCD_VAL(rs.getString("CMCD_VAL"));
					CM_COMMCD.setCMCD_NM(rs.getString("CMCD_NM"));
					CM_COMMCD.setUSE_YN(rs.getString("USE_YN"));
					CM_COMMCD.setLAST_CHNG_USER(rs.getString("LAST_CHNG_USER"));
					CM_COMMCD.setLAST_CHNG_DT(rs.getString("LAST_CHNG_DT"));

					CM_COMMCD.setCOUNT(1);
					
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

			return CM_COMMCD;
		}

		// 조회 (다건)
		public ArrayList<CM_COMMCD> getRecord(CM_COMMCD inCM_COMMCD) {

			StringBuilder strSQL = new StringBuilder();
			DBConnection dbCon = new DBConnection();
			ResultSet rs = null;

			ArrayList<CM_COMMCD> list = new ArrayList<CM_COMMCD>();
			CM_COMMCD CM_COMMCD = null;

			int cnt = 0;

			try {

				// SQL
				strSQL.append("SELECT	*																	" + "\n");
				strSQL.append("FROM		CM_COMMCD															" + "\n");
				strSQL.append("WHERE	1 = 1																" + "\n");

				if (inCM_COMMCD.getCMCD_ID() != null && inCM_COMMCD.getCMCD_ID().equals("") == false) {
					strSQL.append("AND	CMCD_ID = '" + inCM_COMMCD.getCMCD_ID() + "'						" + "\n");
				}

				strSQL.append(";");

				// SQL 실행
				rs = dbCon.SQLExecute("G", strSQL.toString());

				// 데이터 셋팅
				while (rs.next()) {

					cnt++;

					CM_COMMCD = new CM_COMMCD();

					CM_COMMCD.setCMCD_ID(rs.getString("CMCD_ID"));
					CM_COMMCD.setCMCD_VAL(rs.getString("CMCD_VAL"));
					CM_COMMCD.setCMCD_NM(rs.getString("CMCD_NM"));
					CM_COMMCD.setUSE_YN(rs.getString("USE_YN"));
					CM_COMMCD.setLAST_CHNG_USER(rs.getString("LAST_CHNG_USER"));
					CM_COMMCD.setLAST_CHNG_DT(rs.getString("LAST_CHNG_DT"));
							
					CM_COMMCD.setCOUNT(cnt);

					list.add(CM_COMMCD);
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
