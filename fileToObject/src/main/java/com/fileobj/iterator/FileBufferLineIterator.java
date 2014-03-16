package com.fileobj.iterator;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.fileobj.exception.ExceptionUtil;
/**
 * @author rdhabalia
 *
 */
public class FileBufferLineIterator implements FileIterator{

	private static final Logger log = Logger.getLogger(FileBufferLineIterator.class);
	
	private Set<Character> delimiters = new HashSet<Character>();
	private static final Character NEW_LINE = '\n';
	private InputStream in = null;
	private String currentLine = "";
	private FileReader fileReader;

	public boolean hasNext() {

		return currentLine!=null;
		
	}


	public String next() {
		
		try {
			currentLine = readLine();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		if(currentLine==null){
			return "";
			}
		return currentLine;
	}

	private FileBufferLineIterator(Set<Character> endLineChars){
		
		if(endLineChars!=null && !endLineChars.isEmpty()){
			delimiters = endLineChars;
		}else{
			delimiters.add(NEW_LINE);
		}
	}
	
	public FileBufferLineIterator(String path, FileReader reader) throws IOException{

		this(null);
		fileReader = reader;
		in =  (InputStream) fileReader.getFileReadStreamResponse(path); 

	}
	
	public FileBufferLineIterator(String path, FileReader reader, Set<Character> endLineChars) throws IOException{

		this(endLineChars);
		fileReader = reader;
		if(fileReader!=null){
			in =  (InputStream) fileReader.getFileReadStreamResponse(path);
		}else{
			in =  new FileInputStream(path);
		}
		 
		
		
	}
	
	
	public FileBufferLineIterator(String path, Set<Character> endLineChars) throws IOException{

		this(endLineChars);
		in =  new FileInputStream(path); 

	}

	
	public FileBufferLineIterator(InputStream input, Set<Character> endLineChars) throws IOException{

		this(endLineChars);
		in =  input; 

	}

	
	
	
	public void setFileReader(FileReader reader) {
		
		fileReader = reader;
		
	}
	
	/**************************** Helper Methods 
	 * @throws IOException ************************************/
	
	private String readLine() throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		int c = 0;
		while ((c = in.read()) != -1) {
			if (c != 0) {
				char chr = (char) c;
				if(delimiters.contains(chr)){
					break;
				}
				buffer.append(chr);
			} else {
				break;
			}
		}
		
		if(buffer.length()==0){
			return null;
		}else{
			return buffer.toString();
		}
		
		
	}	

	protected void finalize() throws Throwable {

		try {
	    	 if(in!=null){
	    		 in.close();
	    	 }
	     } finally {
	         super.finalize();
	     }
	 }

	public void close() {
		 if(in!=null){
    		 try {
				in.close();
			} catch (IOException e) {
				ExceptionUtil.logException(e, Level.ALL);
			}
    	 }
	}


		  
}
