package com.tech.obfil.bean;

import java.util.List;

import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.FileConfiguration;

/**
 * 
 * @author rdhabalia
 *	FileChunk is File conetent with file name information
 */
public class FileChunk {

	List<ClassificationData> data;
	FileConfiguration file;
	public FileChunk(List<ClassificationData> chunkData, FileConfiguration chunkfile) {
		
		this.data = chunkData;
		this.file = chunkfile;
		
	}
	
	public FileConfiguration getFile() {
		return file;
	}
	public void setFile(FileConfiguration file) {
		this.file = file;
	}

	public List<ClassificationData> getData() {
		return data;
	}

	public void setData(List<ClassificationData> data) {
		this.data = data;
	}
	
}
