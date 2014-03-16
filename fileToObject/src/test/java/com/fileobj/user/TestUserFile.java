package com.fileobj.user;

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
import com.fileobj.enums.FileReadingType;
import com.fileobj.exception.BaseAppException;
import com.fileobj.iterator.FileReader;
import com.fileobj.iterator.factory.RecordIteratorFactory;
import com.fileobj.iterator.factory.SimpleRecordIterator;


/**
 * Unit test for simple App.
 */
public class TestUserFile 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestUserFile( String testName )
    {
        //super( testName );
    }

  
    public static void main(String[] args){
    	TestUserFile user = new TestUserFile("user test");
    	user.process();
    }
    
    public void process()
    {
    	
    	
    	Config config = new Config();
    	config.setFileReadingType(FileReadingType.BUFFER_READING);
    	config.setFileReader(new FileInputStreamReader("src/test/java/com/fileobj/user/user.txt"));
    	
    	//Meta
    	ClassClassificationConfig<UserMeta> classification1 = new ClassClassificationConfig<UserMeta>();
    	RecordClassifier classifier1 = new UserCustomRecordClassifier();
    	classifier1.getPatterns().add("H~");
    	classification1.setClassifier(classifier1);
    	classifier1.setClassifierName("meta");
    	classifier1.setMetaData(true);
    	classification1.setClassName(com.fileobj.user.UserMeta.class);
    	classification1.getRecordBreaker().getDelimiters().add("~");
    	classification1.setReturnEmptyField(true);
    	//Data
    	ClassClassificationConfig<UserData> classification2 = new ClassClassificationConfig<UserData>();
    	RecordClassifier classifier2 = new UserCustomRecordClassifier();
    	classifier2.getPatterns().add("D~");
    	classification2.setClassifier(classifier2);
    	classifier2.setClassifierName("data");
    	classification2.setClassName(com.fileobj.user.UserData.class);
    	classification2.getRecordBreaker().getDelimiters().add("~");
    	classification2.setReturnEmptyField(true);
    	//Error
    	ClassClassificationConfig<UserSummary> classification3 = new ClassClassificationConfig<UserSummary>();
    	RecordClassifier classifier3 = new UserCustomRecordClassifier();
    	classifier3.getPatterns().add("S~");
    	classification3.setClassifier(classifier3);
    	classifier3.setClassifierName("summary");
    	classifier3.setMetaData(true);
    	classification3.setClassName(com.fileobj.user.UserSummary.class);
    	classification3.getRecordBreaker().getDelimiters().add("~");
    	classification3.setReturnEmptyField(true);
    	
    	
    	List<ClassClassificationConfig> classificationConfigs = new ArrayList<ClassClassificationConfig>();
    	classificationConfigs.add(classification1);
    	classificationConfigs.add(classification2);
    	classificationConfigs.add(classification3);
    	
    	
    	SimpleRecordIterator iterator = null;
		try {
			 iterator = RecordIteratorFactory.createSimpleIterator(config,classificationConfigs);
			
			while(iterator.hasNext()){
	    		@SuppressWarnings("rawtypes")
	    		RecordIdentity record = iterator.next();
	    		System.out.println(record);
	    	}
			
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