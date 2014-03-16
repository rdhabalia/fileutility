package com.tech.obfil.distributor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.bean.Record;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.executor.DefaultFeedProcessor;
import com.tech.obfil.executor.FeedProcessor;
import com.tech.obfil.feeder.TemplateFeeder;
import com.tech.obfil.formatter.Template;

/**
 * 
 * @author rdhabalia
 *
 */
public class DefaultChunkGenerator implements ChunkGenerator{


	private static final char WILD_CHAR = '$';
	private List<String> generatedFileName;
	
	public List<FileChunk> generateFileChunk(List<ClassificationData> data, Configuration config) throws BaseAppException{

		List<FileChunk> fileChunks = new ArrayList<FileChunk>();
		try{
			int limit = computeTotalIteration(data);
			FileConfiguration file = config.getFile();
			
			for(int i =0; i<limit; i++){

				List<ClassificationData> dataChunks = new ArrayList<ClassificationData>();
				String fileName = prepareFileName(file,i);
				FileConfiguration chunkfile = new FileConfiguration(file.getDirectoryLocation(), fileName,file.getMimeType(),file.getUniqueDistributePostFixId());
				
				for(ClassificationData classificationData: data){
					if(!classificationData.isMetaData()){
						List<Record> records = getChunk(classificationData,i);
						ClassificationData dataChunk = new ClassificationData(classificationData.getTemplate(),records,classificationData.isMetaData(),classificationData.getTotalRecordPerFile());
						dataChunks.add(dataChunk);
					}else{
						dataChunks.add(classificationData);
					}
				}
				
				FileChunk fileChunk = new FileChunk(dataChunks,chunkfile);
				fileChunks.add(fileChunk);
				
			}
			
			
		}finally{
			//log
		}

		return fileChunks;
		
	}
	

	protected int computeTotalIteration(List<ClassificationData> classificationData) {
		
		int totalIteration = 0;
		
		for(ClassificationData data : classificationData){
			
			if(!data.isMetaData()){
				int maxLimit = data.getTotalRecordPerFile();
				int totalSize = data.getRecords().size();
				if(totalSize%maxLimit==0){
					/*
					 * 1. totalSize=20 and maxLimit =10
					 * => totalSize%maxLimit = 0
					 * => we need exact 2. But if
					 * 2. totalSize=22 and maxLimit =10
					 * => totalSize%maxLimit = 2
					 * => we need 2 + 1 iteration.
					 */
					totalIteration = (totalSize/maxLimit);
				}else{
					totalIteration = (totalSize/maxLimit) + 1 ;	
				}
				
				break;
			}
			
		}
		
		return totalIteration;
	}
	
	
	
	protected List<Record> getChunk(ClassificationData classificationData, int i) {
		int totalRecordSize = classificationData.getRecords().size();
		int slot = classificationData.getTotalRecordPerFile();
		int startCheckPoint = i * slot;
		int endCheckPoint = startCheckPoint+slot;
		if(endCheckPoint > totalRecordSize){
			endCheckPoint = totalRecordSize;
		}
		
		return (classificationData.getRecords().subList(startCheckPoint,endCheckPoint ));
		
	}
	
	
	protected String prepareFileName(FileConfiguration file, int i) {
		
		StringBuffer buffer = new StringBuffer();
		String fileName = prepareFileName(file.getFileLocation(),i);
		
		buffer.append(fileName);
		buffer.append(file.getUniqueDistributePostFixId());
		buffer.append(file.getMimeType());
		
		return buffer.toString();
	}

	public String prepareFileName(String fileName, int i) {
		
		int startIndex = fileName.indexOf(WILD_CHAR);
		if(startIndex!=-1){
			int endIndex =fileName.lastIndexOf(WILD_CHAR);
			int totalWildCharacter = endIndex - startIndex + 1;
			String padding = getPadding(Integer.toString(i),totalWildCharacter);
			String wildString = getWildCharPadding(WILD_CHAR,totalWildCharacter);
			fileName = fileName.replace(wildString, padding);
		}
		
		return fileName;
	}


	protected String getWildCharPadding(char wildChar, int totalWildCharacter) {
		
		String wildString = "";
		for(int i=0; i<totalWildCharacter; i++){
			wildString += WILD_CHAR;
		}
		return wildString;
	}


	protected String getPadding(String number, int totalWildCharacter) {
		
		int numLength = number.length();
		int paddingLength = totalWildCharacter - numLength;
		String padding =number;
		for(int i=0; i<paddingLength;i++){
			padding="0"+padding;
		}
		return padding;
	}


	protected void startFeedProcessing(Template template, List<Record> records,TemplateFeeder feeder, FileConfiguration config) throws BaseAppException {
		FeedProcessor executor = new DefaultFeedProcessor();
		executor.process(template, records, feeder , config);
		
	}



	public void addFileNameToList(FileConfiguration chunkFile) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(chunkFile.getDirectoryLocation());
		buffer.append(File.separator);
		buffer.append(chunkFile.getFileLocation());
		getGeneratedFileName().add(buffer.toString());
		
	}
	
	public List<String> getGeneratedFileName() {
		if(generatedFileName==null){
			generatedFileName = new ArrayList<String>();
		}
		return generatedFileName;
	}



}
