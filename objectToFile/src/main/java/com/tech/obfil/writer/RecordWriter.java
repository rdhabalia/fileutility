package com.tech.obfil.writer;

/**
 * Generic File writer to write files.
 * <p>
 * <h3>Implement FileDistributor</h3>
 * 
 * <pre>
 * FileDistributor fileWriter = RecordDistributor();
 * fileWriter.writeData(..);
 * </pre>
 * 
 * <pre>
 *	Object of this class writes file and gives number of chunk files it created from given content.
 * </pre>
 * 
 * @author rdhabalia 
 */
import com.tech.obfil.exception.BaseAppException;

public interface RecordWriter<T> {

	/**
	 * Write content into defined file.
	 * 
	 * @param data
	 *            The content that should be written into file.
	 * @param append
	 *            if content should be appended into existing file, pass true
	 *            else it will create new file everytime.
	 * @param doFlush
	 *            flushes and writes data after each invocation of this method.
	 * @throws ProcessingException
	 */
	void writeData(T data, boolean append, boolean doFlush)
			throws BaseAppException;

	/**
	 * Closes the stream and file for the file writer.
	 */
	void close() throws BaseAppException;
	
	Object getWriter();

}
