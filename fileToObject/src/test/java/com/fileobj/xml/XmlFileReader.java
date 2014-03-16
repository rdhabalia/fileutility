package com.fileobj.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.management.RuntimeErrorException;

import com.fileobj.iterator.FileReader;

public class XmlFileReader implements FileReader{

	@Override
	public InputStream getFileReadStreamResponse(String path) throws RuntimeException {
		try {
			return new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
