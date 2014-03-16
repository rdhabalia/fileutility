package com.fileobj.iterator;

import java.io.IOException;
import java.util.Set;

import com.fileobj.enums.FileReadingType;

/**
 * 
 * @author rdhabal
 *
 */
public class FileIteratorFactory {

	public static FileIterator getFileIterator(String srcPath,FileReadingType fileReadingType,Set<Character> newLineDelimiter, FileReader reader) throws IOException {

		if(fileReadingType.equals(FileReadingType.BUFFER_READING)){
			return new FileBufferLineIterator(srcPath,reader,newLineDelimiter);
		}else{
			return new FileLineIterator(srcPath);
		}
		
	}

		
}
