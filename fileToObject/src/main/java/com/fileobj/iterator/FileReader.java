package com.fileobj.iterator;

import java.io.InputStream;

/**
 * This reader can be plugged in to read stream by custom reader (i.e.: read file using FTP over the network)
 * @author rdhabalia
 *
 */
public interface FileReader {

	public InputStream getFileReadStreamResponse(String path) ;
	
	
}
