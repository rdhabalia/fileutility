package com.tech.obfil.test;

import java.util.ArrayList;
import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.bean.Text;
import com.tech.obfil.bean.Token;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.executor.DefaultFeedProcessor;
import com.tech.obfil.executor.FeedProcessor;
import com.tech.obfil.formatter.DefaultTextFileTemplate;
import com.tech.obfil.formatter.Template;

public class FileTest {

	public void test(){
		
		String fileName = "tracking.txt";
//		FileConfiguration fileConfig = new FileConfiguration("D:/temp", fileName,".txt","123");
//		TemplateFeeder feeder = new DefaultTemplateFeeder();
//		Configuration config = new Configuration(fileConfig);
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
		List<Record> data = getTrackingData();
		ClassificationData dataClassification = new ClassificationData(dataTemplate,data,false,10);
		layoutChunk.add(dataClassification);
		
		Template summaryTemplate = new  DefaultTextFileTemplate("Summary");
		summaryTemplate.appendElement(new Text("Total~"));
		summaryTemplate.appendElement(new Text(Integer.toString(data.size())));
		ClassificationData summaryClassification = new ClassificationData(summaryTemplate,null,true,1);
		layoutChunk.add(summaryClassification);
		
		FeedProcessor executor = new DefaultFeedProcessor();
		System.out.println(executor);
		
		
	}

	private List<Record> getTrackingData() {
		
		List<Record> records = new ArrayList<Record>();
		
		for(int i =0; i<8;i++){
			Record record = new FileRecord("94342354254"+i,"95050","95125");
			records.add(record);
		}
		
		return records;
	}
	
	
	public static void main(String[] args){
		
		FileTest test = new FileTest();
		test.test();
	}
	
}
