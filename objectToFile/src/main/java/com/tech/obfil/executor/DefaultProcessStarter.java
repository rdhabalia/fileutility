package com.tech.obfil.executor;

import java.util.List;
import java.util.logging.Logger;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.distributor.ChunkGenerator;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.processing.RecordBatchFactory;

public class DefaultProcessStarter implements BaseStarter{

	private static final Logger logger = Logger.getLogger(DefaultProcessStarter.class.getName());
	
	public void executeFeedProcessing(List<ClassificationData> data, Configuration config) throws BaseAppException {

		try{
			ChunkGenerator chunkGenerator = config.getGenerator();

			List<FileChunk> fileChunks = chunkGenerator.generateFileChunk(data, config);
			
			for(FileChunk fileChunk: fileChunks){
				/********************** Logging **********************/
				String message = "Started writing file: "+fileChunk.getFile().getDirectoryLocation()+"/"+fileChunk.getFile().getFileLocation();
				//TEMP logging
				System.out.println(message);
				/********************** Logging **********************/
				/************** Processing ******************************/
				startFeedProcessing(fileChunk,config);
			}
			completeFeedProcessing(config);
			

		}finally{
			//log
		}
				
	}


	protected void startFeedProcessing(FileChunk fileChunk,Configuration config) throws BaseAppException {
		
		RecordBatchFactory.getProcess(config.getProcessingType()).execute(fileChunk,config);

	}
	
	protected void completeFeedProcessing(Configuration config) {
	
		RecordBatchFactory.getProcess(config.getProcessingType()).close();
		
	}
	

}
