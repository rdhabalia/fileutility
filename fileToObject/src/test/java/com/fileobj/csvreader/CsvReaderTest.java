package com.fileobj.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;

import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordClassifier;
import com.fileobj.csv.PatternClassifier;
import com.fileobj.csv.TrackingRecord;
import com.fileobj.exception.BaseAppException;
import com.fileobj.iterator.factory.OpenCSVIterator;
import com.fileobj.iterator.factory.RecordIteratorFactory;

import org.testng.annotations.Test;

public class CsvReaderTest {

	
	
	@Test
	public void process()
    {
    	
    	
    	FileReader reader;
		try {
			reader = new FileReader(new File("src/test/java/com/fileobj/csvreader/gsParcelData.csv"));
			
			//Meta
	    	ClassClassificationConfig<TrackingRecord> classificationConfig = new ClassClassificationConfig<TrackingRecord>();
	    	RecordClassifier classifier1 = new PatternClassifier();
	    	classifier1.getPatterns().add("*");
	    	classificationConfig.setClassifier(classifier1);
	    	classifier1.setClassifierName("trackingRecord");
	    	classifier1.setMetaData(true);
	    	classificationConfig.setClassifier(classifier1);
	    	classificationConfig.setClassName(com.fileobj.csv.TrackingRecord.class);
	    	classificationConfig.setRecordsPerFile(100);
	    	classificationConfig.getRecordBreaker().getDelimiters().add(",");
	    	classificationConfig.setReturnEmptyField(true);
	    	
	    	
	    	
	    	OpenCSVIterator<TrackingRecord> iterator = null;
			try {
				
				iterator = RecordIteratorFactory.createOpenCSVIterator(new CSVReader(reader), classificationConfig);
				int count = 0;
				while(iterator.hasNext()){
		    		@SuppressWarnings("rawtypes")
		    		TrackingRecord record = iterator.next();
		    		count++;
		    		System.out.println(record);
		    	}
				iterator.hasNext();
				System.out.println("Total Count: "+count);
				
			} catch (BaseAppException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    }
    
	
}
