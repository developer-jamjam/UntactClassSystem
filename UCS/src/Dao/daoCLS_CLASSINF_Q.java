package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Domain.CLS_CLASSINF;
import Util.DBConnection;

public class daoCLS_CLASSINF_Q {

	// 조회 (다건)
	public ArrayList<CLS_CLASSINF> getRoomList(CLS_CLASSINF clsInput) {

		StringBuilder strSQL = new StringBuilder();
		DBConnection dbCon = new DBConnection();
		ResultSet rs = null;

		ArrayList<CLS_CLASSINF> list = new ArrayList<CLS_CLASSINF>();
		CLS_CLASSINF cls = null;

		int cnt = 0;

		try {

			// SQL
			strSQL.append("SELECT			R.*																" + "\n");
			strSQL.append("					, C1.CMCD_NM	AS	CLASS_TYPE_NM								" + "\n");
			strSQL.append("					, C2.CMCD_NM	AS	CLASS_DATE_NM								" + "\n");
			strSQL.append("					, C3.CMCD_NM	AS	CLASS_TIME_NM								" + "\n");
			strSQL.append("					, C5.CMCD_NM	AS	CALL_TYPE_NM								" + "\n");
			strSQL.append("FROM				CLS_CLASSINF	AS	R												" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C1	ON	C1.CMCD_VAL = R.CLASS_TYPE				" + "\n");
			strSQL.append("										AND	C1.CMCD_ID = 'CLASS_TYPE'				" + "\n");
			strSQL.append("										AND	C1.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C2	ON	C2.CMCD_VAL = R.CLASS_DATE				" + "\n");
			strSQL.append("										AND	C2.CMCD_ID = 'CLASS_DATE'				" + "\n");
			strSQL.append("										AND	C2.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C3	ON	C3.CMCD_VAL = R.CLASS_TIME				" + "\n");
			strSQL.append("										AND	C3.CMCD_ID = 'CLASS_TIME'				" + "\n");
			strSQL.append("										AND	C3.USE_YN = 'Y'							" + "\n");
			strSQL.append("LEFT OUTER JOIN	CM_COMMCD	AS	C5	ON	C5.CMCD_VAL = R.CALL_TYPE				" + "\n");
			strSQL.append("										AND	C5.CMCD_ID = 'CALL_TYPE'				" + "\n");
			strSQL.append("										AND	C5.USE_YN = 'Y'							" + "\n");
			strSQL.append("WHERE	1 = 1																	" + "\n");

			if (clsInput.getCLASS_NO() != null && clsInput.getCLASS_NO().equals("") == false) {
				strSQL.append("AND	CLASS_NO = '" + clsInput.getCLASS_NO() + "'								" + "\n");
			}

			if (clsInput.getOPEN_USER() != null && clsInput.getOPEN_USER().equals("") == false) {
				strSQL.append("AND	OPEN_USER = '" + clsInput.getOPEN_USER() + "'							" + "\n");
			}

			if (clsInput.getCNCL_YN() != null) {
				if (clsInput.getCNCL_YN().equals("Y")) {
					strSQL.append("AND	CNCL_YN = 'Y'														" + "\n");
				} else {
					strSQL.append("AND	CNCL_YN IN ('', 'N')												" + "\n");
				}
			}

			if (clsInput.getAPPROVE_YN() != null) {
				if (clsInput.getAPPROVE_YN().equals("Y")) {
					strSQL.append("AND	APPROVE_YN = 'Y'													" + "\n");
				} else {
					strSQL.append("AND	APPROVE_YN IN ('', 'N')												" + "\n");
				}
			}

			strSQL.append(";");

			System.out.println(strSQL.toString());

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
				cls.setCLASS_TYPE_NM(rs.getString("CLASS_TYPE_NM"));
				cls.setCLASS_DATE(rs.getString("CLASS_DATE"));
				cls.setCLASS_DATE_NM(rs.getString("CLASS_DATE_NM"));
				cls.setCLASS_TIME(rs.getString("CLASS_TIME"));
				cls.setCLASS_TIME_NM(rs.getString("CLASS_TIME_NM"));
				cls.setOPEN_USER(rs.getString("OPEN_USER"));
				cls.setOPEN_DATE(rs.getString("OPEN_DATE"));
				cls.setCLOSE_DATE(rs.getString("CLOSE_DATE"));
				cls.setCALL_YN(rs.getString("CALL_YN"));
				cls.setCALL_TYPE(rs.getString("CALL_TYPE"));
				cls.setCALL_TYPE_NM(rs.getString("CALL_TYPE_NM"));
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
