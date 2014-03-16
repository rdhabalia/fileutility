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

public class FielBigTest {

	public void test(){
		
		String fileName = "tracking.txt";
		FileConfiguration fileConfig = new FileConfiguration("D:/temp/batch", fileName,".txt","123");
		
//		TemplateFeeder feeder = new DefaultTemplateFeeder();
		ChunkGenerator generator = new FileFeedGenerator();
		BaseStarter starter = new DefaultProcessStarter();
		
		
		Configuration config = new Configuration(fileConfig, generator);
		//config.setProcessingType(FileEnum.ASYNC);
		List<ClassificationData> layoutChunk = new ArrayList<ClassificationData>();
		
		Template headerTemplate = new  DefaultTextFileTemplate("Header");
		headerTemplate.appendElement(new Text("Header~"+fileName));
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
		dataTemplate.appendElement(new Text("~"));
		dataTemplate.appendElement(new Token(7));
		List<Record> data = getTrackingData();
		ClassificationData dataClassification = new ClassificationData(dataTemplate,data,false,9000);
		layoutChunk.add(dataClassification);
		
		Template summaryTemplate = new  DefaultTextFileTemplate("Summary");
		summaryTemplate.appendElement(new Text("Total~"));
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
		
		for(int i =0; i<2200000;i++){
			Record record = new FileRecord("94342354254"+i,"95050","95125","Active","Leg"+i,"San Jose Warehouse","2012-12-12 12:12:21",i%5);
			records.add(record);
		}
		
		return records;
	}
	
	
	public static void main(String[] args){
		
		FielBigTest test = new FielBigTest();
		test.test();
	}
	
}
