package com.tech.obfil.processing;

import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.exception.ErrorCodeEnum;
import com.tech.obfil.executor.DefaultFeedProcessor;
import com.tech.obfil.executor.FeedProcessor;
import com.tech.obfil.feeder.TemplateFeeder;


public class RecordAsyncBatchProcess extends RecordBatchProcess{

	private static ExecutorService executor = Executors.newFixedThreadPool(Configuration.getTotalAsyncThread());
	
	private static Hashtable<String,FutureTask> taskQueue = new Hashtable<String,FutureTask>();
	
	private static final int QUEUE_SIZE = Configuration.getTotalAsyncThread();
	
	private String threadId = null;
	
	private static final String CAL_TYPE = "FILOB.BATCH";
	
	private static final String DOMAIN = "RecordAsyncBatchProcess";
	
	private static final Logger logger = Logger.getLogger(RecordAsyncBatchProcess.class.getName());
	
	public void execute(final FileChunk fileChunk,final Configuration config) throws BaseAppException{

		/*
		 * This maintains Thread Queue size and let not increase more then configured Total Async thread.
		 */
		if(taskQueue.size()>=QUEUE_SIZE){
			lock();
		}
		
		try {
			final TemplateFeeder feeder = (TemplateFeeder) config.getFeeder().newInstance();
			threadId =  UUID.randomUUID().toString();
			FutureTask<String> future = new FutureTask<String>(
					new Callable<String>() {
						public String call() {
							/*
							 * It will allocate file_writing task to each thread.
							 */
							FeedProcessor processor = new DefaultFeedProcessor();
							try {
								processor.process(fileChunk.getData(), fileChunk.getFile(), feeder);
							} catch (BaseAppException e) {
								logger.log(Level.ALL,"RecordAsyncBatchProcess",e);
							}
							return ""+taskQueue.remove(threadId);
						}
					});

			taskQueue.put(threadId,future);
			
			executor.execute(future);
		} catch (InstantiationException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002);
		} catch (IllegalAccessException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002);
		}
		
		

	}
	
	
	public void close(){
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
//			CalEventHelper.writeLog(CAL_TYPE, DOMAIN+".close", e.getMessage(), "0");
		}
	}

	public static boolean isQueueAvailable(){
		
		if(taskQueue.size()<QUEUE_SIZE){
			return true;
		}

		return false;
	}
	
	public void lock() {
		
		while(!isQueueAvailable()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
//				CalEventHelper.writeLog(CAL_TYPE, DOMAIN+".lock", e.getMessage(), "0");
			}
		}
		
	}

}
