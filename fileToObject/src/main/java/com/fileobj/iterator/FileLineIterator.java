package com.fileobj.iterator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.exception.ExceptionUtil;
/**
 * @author rdhabalia
 *
 */
public class FileLineIterator implements FileIterator{

	private static final Logger logger = Logger.getLogger(FileLineIterator.class.getName());
	
	private LineIterator lineIterator = null;

	public boolean hasNext() {

		boolean hasNext = lineIterator.hasNext();

		if(!hasNext){
			LineIterator.closeQuietly(lineIterator);
		}
		return hasNext;
	}

	public String next() {
		return (String) lineIterator.next();
	}

	public FileLineIterator(String path) throws IOException{

		File file = new File(path);
		//TODO check if file is not created yet.
		lineIterator = FileUtils.lineIterator(file, "UTF-8");

	}

	protected void finalize() throws Throwable {

		try {
	    	 LineIterator.closeQuietly(lineIterator);
	     } finally {
	         super.finalize();
	     }
	 }

	public void close() {
		LineIterator.closeQuietly(lineIterator);
	}

	public FileIterator getNewInstance(String path) throws BaseAppException {
		
		try {
			return new FileLineIterator(path);
		} catch (IOException e) {
			ExceptionUtil.logException(e, Level.ALL);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002,"path: "+path);
		}
		
	}

	public void setFileReader(FileReader reader) {
		// TODO Auto-generated method stub
		
	}

}
