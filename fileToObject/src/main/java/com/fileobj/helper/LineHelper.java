package com.fileobj.helper;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.fileobj.bean.SimpleRecord;
import com.fileobj.classifier.RecordBreaker;
import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.exception.ExceptionUtil;
/**
 * @author rdhabalia
 *
 */
public class LineHelper {

	public static SimpleRecord getRecordsFromLine(String line, RecordBreaker breaker, boolean isEmptyTokenReturn) throws BaseAppException   {

		breaker.generateTokensForLine(line,isEmptyTokenReturn);
		
		SimpleRecord record = new SimpleRecord();
		try {
			while(breaker.hasMoreTokens()){
				String temp = breaker.nextToken();
				record.getColumnSet().add(temp);
			}
		} catch (SecurityException e) {
			ExceptionUtil.logException(e, Level.ALL,line);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003);
		} catch (IllegalArgumentException e) {
			ExceptionUtil.logException(e, Level.ALL,line);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003);
		}
		return record;
	}
}
