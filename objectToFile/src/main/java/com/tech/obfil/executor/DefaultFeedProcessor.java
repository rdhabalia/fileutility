package com.tech.obfil.executor;

import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.feeder.TemplateFeeder;
import com.tech.obfil.formatter.Template;

/**
 * 
 * @author rdhabalia
 *
 */
public class DefaultFeedProcessor implements FeedProcessor{

	public void process(List<ClassificationData> layoutChunk, FileConfiguration file,TemplateFeeder feeder) throws BaseAppException{

		try{
			feeder.start(file);
			
			for(ClassificationData chunk: layoutChunk){
				try {
					feeder.feed(chunk.getTemplate(), chunk.getRecords());
				} catch (BaseAppException e) {
					throw e;
				}
			}

		}finally{
			//log
		}
		
	}
	
	public void process(Template template, List<Record> records, TemplateFeeder feeder, FileConfiguration file) throws BaseAppException{

		
		try {
			feeder.start(file);
			feeder.feed(template, records);
		} catch (BaseAppException e) {
			throw e;
		}finally{
			//log
		}
		
	}
	
	
	
}
