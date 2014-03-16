package com.tech.obfil.distributor;

import java.util.List;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.exception.BaseAppException;

/**
 * 
 * @author rdhabalia
 *	This Class generates FileChunk out of huge File content. 
 *	This class will be overriden by application when application requires to add element into template 
 *	at runtime. 
 *
 */
public interface ChunkGenerator {

	/**
	 * This method consumes entire list of file conentent and returns into part of chunk.
	 * @param data: All list of data content
	 * @param config: Configuration
	 * @return List<FileChunk> : each FileChunk represents one File and 
	 * 							it has appropriate Template and data content.
	 */
	List<FileChunk> generateFileChunk(List<ClassificationData> data, Configuration config)throws BaseAppException;

	/**
	 * It provides list of all generated files
	 * @return
	 */
	List<String> getGeneratedFileName();
	
}
