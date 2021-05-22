package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	SimpleDateFormat dfDtYMD = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dfStrYMD = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat dfStrYMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");

	public StringUtil() {
		// TODO Auto-generated constructor stub
	}

	// yyyyMMdd -> yyyy-MM-dd
	public String strToStrDate(String parm) {
		if (parm.length() == 8 || parm.length() == 14) {
			return parm.substring(0, 4) + "-" + parm.substring(4, 6) + "-" + parm.substring(6, 8);
		} else {
			return parm.substring(0, 10);
		}
	}

	// yyyy-MM-dd -> yyyyMMdd
	public String strDateToStrYMD(String parm) {

		if (parm.length() == 0) {
			return parm;
		}

		return parm.replace("-", "").substring(0, 8);
	}

	// Date -> yyyy-MM-dd (String)
	public String dtToStrDate(Date parm) {
		return dfDtYMD.format(parm);
	}

	// Date -> yyyyMMdd (String)
	public String dtToStrYMD(Date parm) {
		System.out.println(parm.toString());
		return dfStrYMD.format(parm);
	}

	// Date -> yyyyMMddHHmmss (String)
	public String dtToStrYMDHMS(Date parm) {
		return dfStrYMDHMS.format(parm);
	}

	// String -> Date (yyyy-MM-dd)
	public Date strDateToDateYMD(String parm) {

		String strDate = strToStrDate(parm);

		Date d = null;

		try {
			d = dfDtYMD.parse(strDate.substring(0, 10));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

	// String -> String (yyyy-MM-dd hh:MM:ss)
	public String strDateToDateYMDHMS(String parm) {

		String strYMD = parm.substring(0, 4) + "-" + parm.substring(4, 6) + "-" + parm.substring(6, 8);
		String strHMS = parm.substring(8, 10) + ":" + parm.substring(10, 12) + ":" + parm.substring(12, 14);

		return strYMD + " " + strHMS;
	}

	//
	public boolean toCheck(String parm) {

		if (parm.equals("Y") == true) {
			return true;
		} else {
			return false;
		}

	}

	public String toYN(boolean parm) {

		if (parm == true) {
			return "Y";
		} else {
			return "N";
		}

	}

}
