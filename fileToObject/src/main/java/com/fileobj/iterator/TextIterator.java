package com.fileobj.iterator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.exception.ExceptionUtil;
/**
 * @author rdhabalia
 *
 */
public class TextIterator implements FileIterator{

	private static final Logger logger = Logger.getLogger(TextIterator.class.getName());
	private FileLineIterator lineIterator;
	
	public TextIterator(String path) throws IOException{
		lineIterator = new FileLineIterator(path);
	}
	public boolean hasNext() {
		return lineIterator.hasNext();
	}

	public Object next() {
		return lineIterator.next();
	}
	public FileIterator getNewInstance(String path) throws BaseAppException {
		try {
			return new TextIterator(path);
		} catch (IOException e) {
			ExceptionUtil.logException(e, Level.ALL);
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20002,"path: "+path);
		}
	}
	
	public void setFileReader(FileReader reader) {
		// TODO Auto-generated method stub
		
	}

}
