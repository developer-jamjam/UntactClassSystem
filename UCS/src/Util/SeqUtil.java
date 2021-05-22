package Util;


import java.util.Date;

import Dao.daoCM_SEQNO;
import Domain.CM_SEQNO;

public class SeqUtil {

	public String getSeq(String strSeqId, String strDate, String strUser) {

		String strYY = strDate.substring(2, 4);
		String strMM = strDate.substring(4, 6);

		// find CM_SEQNO
		CM_SEQNO CM_SEQNO = new CM_SEQNO();
		daoCM_SEQNO daoCM_SEQNO = new daoCM_SEQNO();

		CM_SEQNO.setSEQ_ID(strSeqId);
		CM_SEQNO.setSEQ_YEAR(strYY);
		CM_SEQNO.setSEQ_MONTH(strMM);

		CM_SEQNO = daoCM_SEQNO.findRecord(CM_SEQNO);

		// seq
		String baseSeq = "000000";
		int intSeq = 1;	
		
		if (CM_SEQNO.getCOUNT() > 0) {
			intSeq = CM_SEQNO.getSEQ_NO() + 1;
		}	
		
		String strSeq = baseSeq + Integer.toString(intSeq);
		strSeq = strSeq.substring(strSeq.length() - baseSeq.length(), strSeq.length());
		
		System.out.println(strSeq);
		
		// insert or update
		Date now = new Date();
		
		StringUtil stringUtil = new StringUtil();
		
		CM_SEQNO.setSEQ_ID(strSeqId);
		CM_SEQNO.setSEQ_YEAR(strYY);
		CM_SEQNO.setSEQ_MONTH(strMM);
		CM_SEQNO.setSEQ_NO(intSeq);
		CM_SEQNO.setLAST_CHNG_USER(strUser);
		CM_SEQNO.setLAST_CHNG_DT(stringUtil.dtToStrYMDHMS(now));
		
		if (CM_SEQNO.getCOUNT() == 0) {	
			daoCM_SEQNO.insertRecord(CM_SEQNO);

		} else {
			daoCM_SEQNO.updateRecord(CM_SEQNO);
		}

		return strSeqId + strYY + strMM + strSeq;
	}

}
