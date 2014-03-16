package com.fileobj.iterator.factory;

import com.fileobj.exception.BaseAppException;

/**
 * 
 * @author rdhabal
 *
 * @param <E>
 */
public interface RecordIterator<E> {

	public boolean hasNext();
	
	public E next() throws BaseAppException;
	
}
