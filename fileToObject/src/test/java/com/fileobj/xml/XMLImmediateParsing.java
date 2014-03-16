package com.fileobj.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fileobj.bean.RecordIdentity;
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordClassifier;
import com.fileobj.config.Config;
import com.fileobj.iterator.FileReader;
import com.fileobj.iterator.factory.RecordIteratorFactory;
import com.fileobj.iterator.factory.XMLRecordIterator;

import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class XMLImmediateParsing 
{
   

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
    public void testApp()
    {
    	
    	Config config = new Config();
    	config.setSrcPath("src/test/java/com/fileobj/xml/foundbugs.xml");
    	FileReader xmlFileReader = new XmlFileReader();
		config.setFileReader(xmlFileReader);
    	

    	ClassClassificationConfig classification1 = new ClassClassificationConfig();
    	classification1.setClassName(BugInstance.class);
    	classification1.setRecordsPerFile(10);
    	classification1.setReturnEmptyField(true);
    	
    	
    	List<ClassClassificationConfig> classificationConfigs = new ArrayList<ClassClassificationConfig>();
    	classificationConfigs.add(classification1);
    	
    	
    	
    	try {
    		XMLRecordIterator iterator =RecordIteratorFactory.createXmlIterator(config, classificationConfigs);
    		int count = 0;
    		int sequence=0;
    		while(iterator.hasNext()){
    			List<RecordIdentity> records = iterator.next();
    			if(records!=null){
    				for(int i=0;i<records.size();i++){
    					RecordIdentity obj = records.get(i);
    					count++;
    					if(obj.getClassification().getClassName().equals(BugInstance.class)){
    						if(obj.getRecord() instanceof List){
    							List<BugInstance> bugs = (List<BugInstance>) obj.getRecord();
    							for(BugInstance bug : bugs){
    								System.out.println("Bug count="+(sequence++)+", info="+bug.getType());
    							}
    						}
    					}
    				}
    			}
    		}
    		System.out.println(count++);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
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
