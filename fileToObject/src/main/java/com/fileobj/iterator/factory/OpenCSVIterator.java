package com.fileobj.iterator.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.fileobj.bean.SimpleRecord;
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordClassifier;
import com.fileobj.exception.BaseAppException;
import com.fileobj.feeder.DataFeeder;


public class OpenCSVIterator<T> implements RecordIterator<T> {

	private CSVReader csvReader;
	private ClassClassificationConfig<T> classificationConfig;
	private boolean hasNext=true;
	 private static final Logger log = Logger.getLogger(OpenCSVIterator.class);
	
	OpenCSVIterator(CSVReader csvReader,ClassClassificationConfig<T> classificationConfig){
		this.classificationConfig = classificationConfig;
		this.csvReader = csvReader;
	}
	
	@Override
	public boolean hasNext() {
		if(!hasNext){
			try {csvReader.close();} catch (IOException e) {log.error("CSVreader failed to get closed:", e);}
		}
		return hasNext;
	}

	@Override
	public  T next() throws BaseAppException {
		
		try {
			String[] tokens = csvReader.readNext();
			if(tokens==null){
				hasNext=false;
			}else{
				if(isPatternMatch(classificationConfig.getClassifier(),tokens)){
					SimpleRecord record = createSimpleRecord(tokens);
					List<T> list = feedRecords(classificationConfig,record);
					if(list!=null && !list.isEmpty()){
						return list.get(0);
					}
				}
				
			}
			
		} catch (IOException e) {
			log.error("itenrator.next failed:",e);
		}
		
		return null;
	}

	private boolean isPatternMatch(RecordClassifier classifier, String[] tokens) {
		
		if(classifier!=null && tokens!=null){
			StringBuilder tokenStr = new StringBuilder();
			for(String token : tokens){
				tokenStr.append(token);
			}
			return classifier.doesPatternMatch(tokenStr.toString());
		}
		
		return true;
	}

	private SimpleRecord createSimpleRecord(String[] tokens) {
		
		SimpleRecord record = new SimpleRecord();
		for(String token: tokens){
			record.getColumnSet().add(token);
		}
		return record;
	}

	private List<T> feedRecords(ClassClassificationConfig<T> classificationConfig, final SimpleRecord record) throws BaseAppException {
		
		List<T> data;
		try {
			data = DataFeeder.feedRecord(classificationConfig.getClassName(), new ArrayList<SimpleRecord>(){
				private static final long serialVersionUID = 3986814098920538936L;
			{add(record);}});
			return data;
		} catch (BaseAppException e) {
			log.error("feedRecords failed:", e);
			throw e;
		}
	
	}
	
}
