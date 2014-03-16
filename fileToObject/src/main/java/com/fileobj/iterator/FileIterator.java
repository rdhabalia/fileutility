package com.fileobj.iterator;


/**
 * @author rdhabalia
 *
 */
public interface FileIterator {

	boolean hasNext();
	
	Object next();
	
	void setFileReader(FileReader reader);
	
	
}
