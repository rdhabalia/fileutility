package com.fileobj.distributor;
/**
 * @author rdhabal
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.exception.ExceptionUtil;

public class RecordDistributor implements FileDistributor{

	
	private long recordsPerFile = 0;
	private String postfixData = null;
	private int onGoingLineNumber = 0;
	private int onGoingChunkNumbr = 0;
	private BufferedWriter writer = null;
	private String outputDirectory = null;
	private boolean isChunkNeeded = true;
	private static final Logger logger = Logger.getLogger(RecordDistributor.class.getName());
	public static final String domainName = "FileDistributor";
	private static final String INIT_FAILED = " not initialized..";
	
	public RecordDistributor(long totalRecordsPerFile, String fileName, String outputDir, boolean isChunkNeeded) {
		outputDirectory = outputDir;
		postfixData = fileName;
		recordsPerFile = totalRecordsPerFile;
		this.isChunkNeeded = isChunkNeeded;
		createOutputDirectory();
	}
	
	public boolean writeData(Object lineObj,boolean append,boolean doFlush) throws BaseAppException{
		String line = (String) lineObj;
		return appendRecordInFile(line,append,doFlush);
	}

	public void close() throws BaseAppException {
		try {
			if(writer!=null){writer.close();}
		} catch (IOException e) {
			ExceptionUtil.logException(e, Level.ALL);
			throw new BaseAppException(ErrorCodeEnum.APPLICATION_ERROR_20002,outputDirectory+"."+postfixData+INIT_FAILED);
		}
		
	}
	
	protected boolean appendRecordInFile(String line, boolean append, boolean doFlushEachTime) throws  BaseAppException {

		BufferedWriter bout = getFileWrite(postfixData,append);
		boolean success = true;

		try {
			bout.write(line + "\n");
			if(doFlushEachTime){
				bout.flush();
			}
		} catch (IOException e) {
			ExceptionUtil.logException(e, Level.ALL,line);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003,outputDirectory+"."+postfixData+INIT_FAILED);
		}
		
		return success;
	}

	private BufferedWriter getFileWrite(String postfix, boolean append) throws BaseAppException {
		
		
		if(onGoingLineNumber>=recordsPerFile){
			onGoingLineNumber=0;
			onGoingChunkNumbr++;
			try {
				writer.close();
			} catch (IOException e) {
				throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003,outputDirectory+"."+postfixData+INIT_FAILED);
			}
		}
		
		if(onGoingLineNumber==0){
			try {
				String path = null;
				if(isChunkNeeded){
					path = outputDirectory+File.separator+onGoingChunkNumbr+postfix;
				}else{
					path = outputDirectory+File.separator+postfix;
				}
				writer = new BufferedWriter(new FileWriter(path,append));
				onGoingLineNumber++;
			} catch (IOException e) {
				ExceptionUtil.logException(e, Level.ALL,outputDirectory+"."+postfixData+INIT_FAILED);
				throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003,outputDirectory+"."+postfixData+INIT_FAILED);
			}
		}else{
			onGoingLineNumber++;
		}
		return writer;
	}

	protected void finalize(){
		try {
			writer.close();
		} catch (IOException e) {
			ExceptionUtil.logException(e, Level.ALL,outputDirectory+"."+postfixData+INIT_FAILED);
		}
	}


	public  void createOutputDirectory() {
		File dir = new File(outputDirectory);
		if(!dir.exists()){
			boolean created = dir.mkdir();
			logger.log(Level.ALL,"Directory created :"+created);
		}
		
	}

	protected void delete(File file) throws BaseAppException {

		if (file.isDirectory()) {
			for (File c : file.listFiles()){
				delete(c);
			}
		}
		if (!file.delete()){
			logger.log(Level.ALL,"delete", file.getAbsolutePath()+" not found");
			throw new BaseAppException(ErrorCodeEnum.APPLICATION_ERROR_20002,file.getAbsolutePath()+" not found");
		}

	}

	public int getTotalChunk(){
		return onGoingChunkNumbr;
	}

}
