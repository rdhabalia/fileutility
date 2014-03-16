package com.tech.obfil.test;

import java.util.ArrayList;
import java.util.List;



import com.tech.obfil.bean.FileChunk;
import com.tech.obfil.bean.Record;
import com.tech.obfil.bean.Text;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.distributor.DefaultChunkGenerator;
import com.tech.obfil.formatter.DefaultTextFileTemplate;
import com.tech.obfil.formatter.Template;


public class FileFeedGenerator extends DefaultChunkGenerator{

	public List<FileChunk> generateFileChunk(List<ClassificationData> data, Configuration config) {
		
		int limit = computeTotalIteration(data);
		FileConfiguration file = config.getFile();
		List<FileChunk> fileChunks = new ArrayList<FileChunk>();
		
		for(int i =0; i<limit; i++){

			int totalRecord = 0;
//			Template template = null;
			
			List<ClassificationData> dataChunks = new ArrayList<ClassificationData>();
			String fileName = prepareFileName(file,i);
			
			FileConfiguration chunkfile = new FileConfiguration(file.getDirectoryLocation(), fileName,file.getMimeType(),file.getUniqueDistributePostFixId());
			
			for(ClassificationData classificationData: data){
				if(!classificationData.isMetaData()){
					List<Record> records = getChunk(classificationData,i);
					ClassificationData dataChunk = new ClassificationData(classificationData.getTemplate(),records,classificationData.isMetaData(),classificationData.getTotalRecordPerFile());
					dataChunks.add(dataChunk);
					
					
					totalRecord = records.size();
				}else{
					Template tempTemplate =classificationData.getTemplate();
					if(classificationData.getTemplate().getName().equalsIgnoreCase("Summary")){
						tempTemplate = new  DefaultTextFileTemplate("Summary");
						tempTemplate.appendElement(new Text(classificationData.getTemplate().getFormat()));
						tempTemplate.appendElement(new Text(Integer.toString(totalRecord)));
					}else if(classificationData.getTemplate().getName().equalsIgnoreCase("Header")){
						tempTemplate = new  DefaultTextFileTemplate("Header");
						tempTemplate.appendElement(new Text(classificationData.getTemplate().getFormat()));
						tempTemplate.appendElement(new Text(fileName));
					}
					ClassificationData dataChunk = new ClassificationData(tempTemplate,classificationData.getRecords(),classificationData.isMetaData(),classificationData.getTotalRecordPerFile());
					dataChunks.add(dataChunk);
					
//					FileChunk fileChunk = new FileChunk(chunkData,chunkfile);
//					fileChunks.add(fileChunk);
				}
			}
			FileChunk fileChunk = new FileChunk(dataChunks,chunkfile);
			fileChunks.add(fileChunk);
		}
		 
		return fileChunks;
		
	}


}
