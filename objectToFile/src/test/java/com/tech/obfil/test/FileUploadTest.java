package com.tech.obfil.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.bean.Text;
import com.tech.obfil.bean.Token;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.distributor.ChunkGenerator;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.executor.BaseStarter;
import com.tech.obfil.executor.DefaultProcessStarter;
import com.tech.obfil.formatter.DefaultTextFileTemplate;
import com.tech.obfil.formatter.Template;

public class FileUploadTest {

	public void test(String version, String type, String carrier, String date){
		
		String fileName = "tracking$$$USPS";
		FileConfiguration fileConfig = new FileConfiguration("D:/temp/batch", fileName,".txt","");
		
		ChunkGenerator generator = new FileFeedGenerator();
		BaseStarter starter = new DefaultProcessStarter();
		
		
		Configuration config = new Configuration(fileConfig, generator);
		//config.setProcessingType(FileEnum.ASYNC);
		List<ClassificationData> layoutChunk = new ArrayList<ClassificationData>();
		
		Template headerTemplate = new  DefaultTextFileTemplate("Header");
		StringBuffer buffer = new StringBuffer();
		buffer.append("H");
		buffer.append("~");
		buffer.append(version);
		buffer.append("~");
		buffer.append(type);
		buffer.append("~");
		buffer.append(date);
		buffer.append("~");
		buffer.append(carrier);
		headerTemplate.appendElement(new Text(buffer.toString()));
		ClassificationData headerClassification = new ClassificationData(headerTemplate,null,true,1);
		
		layoutChunk.add(headerClassification);
		
		
		Template dataTemplate = new  DefaultTextFileTemplate("Data");
		dataTemplate.appendElement(new Token(0));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(1));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(2));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(3));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(4));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(5));
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(6));
		List<Record> data = getTrackingData();
		ClassificationData dataClassification = new ClassificationData(dataTemplate,data,false,9900);
		layoutChunk.add(dataClassification);
		
		Template summaryTemplate = new  DefaultTextFileTemplate("Summary");
		summaryTemplate.appendElement(new Text("T~"));
		ClassificationData summaryClassification = new ClassificationData(summaryTemplate,null,true,1);
		layoutChunk.add(summaryClassification);
		
		long start = Calendar.getInstance().getTimeInMillis();
		try {
			starter.executeFeedProcessing(layoutChunk, config);
		} catch (BaseAppException e) {
			e.printStackTrace();
		}
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println("Total Time: "+(end-start)/1000);
		
		
	}

	private List<Record> getTrackingData() {
		
		List<Record> records = new ArrayList<Record>();
		
		for(int i =0; i<10000;i++){
			Record record = new FileDetail("D","9405509699937423258438"+i,"0",null,null,null,null,null);
			records.add(record);
		}
		
		return records;
	}
	
	
	public static void main(String[] args){
		
		FileUploadTest test = new FileUploadTest();
		test.test("1.0", "Tracking", "USPS", "20130129171900054");
	}
	
}
