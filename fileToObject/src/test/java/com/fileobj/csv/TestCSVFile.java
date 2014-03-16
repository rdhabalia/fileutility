package com.fileobj.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fileobj.bean.RecordIdentity;
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordClassifier;
import com.fileobj.config.Config;
import com.fileobj.enums.FileReadingType;
import com.fileobj.exception.BaseAppException;
import com.fileobj.iterator.FileReader;
import com.fileobj.iterator.factory.RecordIteratorFactory;
import com.fileobj.iterator.factory.SimpleRecordIterator;


/**
 * Unit test for simple App.
 */
public class TestCSVFile 
{

	@Test
    public void process()
    {
    	
    	Config config = new Config();
    	
    	config.setFileReadingType(FileReadingType.BUFFER_READING);
    	config.setFileReader(new FileInputStreamReader("src/test/java/com/fileobj/csv/gsParcelData.csv"));
    	
    	
    	//Meta
    	ClassClassificationConfig<TrackingRecord> classification1 = new ClassClassificationConfig<TrackingRecord>();
    	RecordClassifier classifier1 = new PatternClassifier();
    	classifier1.getPatterns().add("*");
    	classification1.setClassifier(classifier1);
    	classifier1.setClassifierName("trackingRecord");
    	classifier1.setMetaData(true);
    	classification1.setClassName(com.fileobj.csv.TrackingRecord.class);
    	classification1.setRecordsPerFile(100);
    	classification1.getRecordBreaker().getDelimiters().add(",");
    	classification1.setReturnEmptyField(true);
    	
    	
    	List<ClassClassificationConfig> classificationConfigs = new ArrayList<ClassClassificationConfig>();
    	classificationConfigs.add(classification1);
    	
    	SimpleRecordIterator iterator = null;
		try {
			 iterator = RecordIteratorFactory.createSimpleIterator(config,classificationConfigs);
			int count=0;
			while(iterator.hasNext()){
	    		@SuppressWarnings("rawtypes")
	    		RecordIdentity record = iterator.next();
	    		System.out.println(record);
	    		count++;
	    	}
			System.out.println(count);
			
		} catch (BaseAppException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    class FileInputStreamReader implements FileReader {


    	private String filePath = null;

    	public FileInputStreamReader(String filePath){
    		this.filePath = filePath;
    	}


    	public InputStream getFileReadStreamResponse(String path) {

    		File file = new File(filePath);
    		InputStream inputStream = null;
    		try {
    			inputStream = new FileInputStream(file);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}

    		return inputStream;
    	}

    }
    
}