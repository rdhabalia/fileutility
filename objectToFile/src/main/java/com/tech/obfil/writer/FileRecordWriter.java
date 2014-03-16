package com.tech.obfil.writer;
/**
 * @author rdhabalia
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.exception.ErrorCodeEnum;


public class FileRecordWriter implements RecordWriter<String>{

	
	private long recordsPerFile = 0;
	private String postfixData = null;
	private int onGoingLineNumber = 0;
	private int onGoingChunkNumbr = 0;
	private BufferedWriter writer = null;
	private String OUTPUT_DIR = null;
	private boolean isNewFile = false;
	private boolean isChunkNeeded = true;
	public static final String domainName = "FileDistributor";
	private static final char WILD_CHAR = '$';
	private static final Logger logger = Logger.getLogger(FileRecordWriter.class.getName());
	
	public FileRecordWriter(long totalRecordsPerFile, String fileName, String outputDir, boolean isChunkNeeded) {
		OUTPUT_DIR = outputDir;
		postfixData = fileName;
		recordsPerFile = totalRecordsPerFile;
		this.isChunkNeeded = isChunkNeeded;
		createOutputDirectory();
	}
	
	public FileRecordWriter(String outputDir , String fileName) {
		OUTPUT_DIR = outputDir;
		postfixData = fileName;
		createOutputDirectory();
	}
	
	
	public void writeData(String line,boolean append,boolean doFlush) throws BaseAppException{
		appendRecordInFile(line,append,doFlush);
	}

	public void close() throws BaseAppException {
		try {
			if(writer!=null)
			writer.close();
		} catch (IOException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002,OUTPUT_DIR+File.separator+postfixData);
		}
		
	}
	
	protected void appendRecordInFile(String line, boolean append, boolean doFlushEachTime) throws BaseAppException {

		BufferedWriter bout = getFileWrite(append);

		try {
			String nl = "\r\n";
			bout.write(line + nl);
			if(doFlushEachTime){
				bout.flush();
			}
		} catch (IOException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002,OUTPUT_DIR+File.separator+postfixData);
		}
		
	}

	
	private BufferedWriter getFileWrite(boolean append) throws BaseAppException {
		
		String path = OUTPUT_DIR+File.separator+postfixData;
		if(writer==null){
			try {
				writer = new BufferedWriter(new FileWriter(path,append));
			} catch (IOException e) {
				logger.log(Level.ALL,"getFileWrite"+ "path: "+path, e);
				throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20001,"path: "+path);
			}
		}
		return writer;
	}
	

	public void finalize(){
		try {
			writer.close();
		} catch (IOException e) {
			logger.log(Level.ALL,domainName, e);
		}
	}


	public  void createOutputDirectory() {
		File dir = new File(OUTPUT_DIR);
		if(!dir.exists()){
			dir.mkdir();
		}
		
	}

	protected void delete(File file) throws FileNotFoundException {

		if (file.isDirectory()) {
			for (File c : file.listFiles())
				delete(c);
		}
		if (!file.delete()){
//			throw new FileNotFoundException("Failed to delete file: " + file);
		}

	}

	public Object getWriter() {
		return postfixData;
	}


	
}
