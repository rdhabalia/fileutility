package com.tech.obfil.processing;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.exception.ErrorCodeEnum;
import com.tech.obfil.executor.DefaultFeedProcessor;
import com.tech.obfil.executor.FeedProcessor;
import com.tech.obfil.feeder.TemplateFeeder;

/**
 * @author rdhabalia
 *
 */
public class RecordSyncBatchProcess extends RecordBatchProcess{

	
	public void execute(FileChunk fileChunk,Configuration config) throws BaseAppException{
		
		try {
			FeedProcessor executor = new DefaultFeedProcessor();
			TemplateFeeder feeder = (TemplateFeeder) config.getFeeder().newInstance();
			executor.process(fileChunk.getData(), fileChunk.getFile(), feeder);
		} catch (InstantiationException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002);
		} catch (IllegalAccessException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002);
		}
		
	}
	
}
