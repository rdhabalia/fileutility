package com.fileobj.config;

import java.util.HashSet;
import java.util.Set;

import com.fileobj.enums.FileReadingType;
import com.fileobj.iterator.FileReader;
/**
 * @author rdhabal
 *
 */
public class Config {

	private String processId;
	private String srcPath;
	private String outputPath;
	private String srcMIMEType;
	private String processType;
	private FileReadingType FILE_READING_TYPE = FileReadingType.LINE_READING;
	private FileReader fileReader;
	private Set<Character> newLineDelimiters = new HashSet<Character>();
	
	public FileReader getFileReader() {
		return fileReader;
	}
	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public String getSrcMIMEType() {
		return srcMIMEType;
	}
	public void setSrcMIMEType(String srcMIMEType) {
		this.srcMIMEType = srcMIMEType;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	public FileReadingType getFileReadingType() {
		return FILE_READING_TYPE;
	}
	public void setFileReadingType(FileReadingType fileReadingType) {
		FILE_READING_TYPE = fileReadingType;
	}
	public Set<Character> getNewLineDelimiters() {
		
		if(newLineDelimiters==null){
			newLineDelimiters = new HashSet<Character>();
			newLineDelimiters.add('\n');
		}
		
		return newLineDelimiters;
	}
	public void setNewLineDelimiters(Set<Character> newLineDelimiter) {
		this.newLineDelimiters = newLineDelimiter;
	}
	
	
}
