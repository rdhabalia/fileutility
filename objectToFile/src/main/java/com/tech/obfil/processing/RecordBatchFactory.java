package com.tech.obfil.processing;

import com.tech.obfil.enums.FileEnum;

/**
 * @author rdhabalia
 *
 */
public class RecordBatchFactory {

	public static RecordBatchProcess getProcess(String type){
		
		RecordBatchProcess process = null;
		
		if(FileEnum.ASYNC.equals(type)){
			process = new RecordAsyncBatchProcess();
		}else{
			process = new RecordSyncBatchProcess();
		}
		
		return process;
		
	}
	
}
