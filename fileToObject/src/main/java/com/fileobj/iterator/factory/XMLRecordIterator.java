package com.fileobj.iterator.factory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamException;

import com.fileobj.bean.RecordIdentity;
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.config.Config;
import com.fileobj.config.RecordClassificationWriter;
import com.fileobj.exception.BaseAppException;
import com.fileobj.iterator.XMLIterator;

/**
 * @author rdhabalia
 *
 */
public class XMLRecordIterator implements RecordIterator<List<RecordIdentity>>{

	private XMLIterator iterator = null;
	
	/**
	 *
	 * @param  config  Common config for file splitting
	 * @param  classificationConfigList Classifier specific config
	 * @return      SplitOutput map of splitted file and classifier
	 * @throws XMLStreamException 
	 * @throws FileNotFoundException 
	 * @throws ProcessingException 
	 * @see         split
	 */
	@SuppressWarnings("rawtypes")
	XMLRecordIterator(Config config, List<ClassClassificationConfig> classificationConfigList) throws BaseAppException, FileNotFoundException, XMLStreamException {
		
		List<RecordClassificationWriter> classificationWriters = new ArrayList<RecordClassificationWriter>();
		
		if(classificationConfigList!=null && !classificationConfigList.isEmpty()){
			
			for(ClassClassificationConfig classificationConfig: classificationConfigList){
				RecordClassificationWriter classificationWriter = new RecordClassificationWriter(classificationConfig);
				classificationWriters.add(classificationWriter);
			}
			
			Package pkg = classificationConfigList.get(0).getClassName().getPackage();
			iterator = new XMLIterator(config.getFileReader().getFileReadStreamResponse(config.getSrcPath()),pkg,classificationConfigList);
		}
		
	}
	
	

	public boolean hasNext() {
		if(iterator!=null){
			return iterator.hasNext();
		}
		return false;
	}


	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RecordIdentity> next() throws BaseAppException {
		if(iterator!=null){
			Map<ClassClassificationConfig,List> records = iterator.next();
			if(records!=null){
				List<RecordIdentity> allRecords = new ArrayList<RecordIdentity>();
				for(Entry<ClassClassificationConfig,List> entry : records.entrySet()){
					allRecords.add(new RecordIdentity(entry.getKey(),entry.getValue()));
				}
				return allRecords;
			}
		}
		return null;
	}
	
	
}
