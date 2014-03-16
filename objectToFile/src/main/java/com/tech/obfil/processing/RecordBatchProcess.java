package com.tech.obfil.processing;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.exception.BaseAppException;

/**
 * @author rdhabalia
 *
 */
public abstract class RecordBatchProcess {

	protected static boolean isAllDone = true;
	
	public abstract void execute(FileChunk fileChunk,Configuration config) throws BaseAppException;	
	
	public static boolean isAllDone(){
		return isAllDone;
	}

	public void close(){
		
	}
}
