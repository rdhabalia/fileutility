package com.fileobj.iterator.factory;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import au.com.bytecode.opencsv.CSVReader;

import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.config.Config;
import com.fileobj.exception.BaseAppException;

/**
 * 
 * @author rdhabal
 *
 */
public class RecordIteratorFactory {

	public static SimpleRecordIterator createSimpleIterator(Config config, List<ClassClassificationConfig> classificationConfigList) throws BaseAppException{
		checkParam(config,classificationConfigList);
		return new SimpleRecordIterator(config,classificationConfigList);
	}
	
	public static XMLRecordIterator createXmlIterator(Config config, List<ClassClassificationConfig> classificationConfigList) throws FileNotFoundException, BaseAppException, XMLStreamException{
		return new XMLRecordIterator(config,classificationConfigList);
	}
	
	public static <T> OpenCSVIterator<T> createOpenCSVIterator(CSVReader  csvReader, ClassClassificationConfig<T> classificationConfig){
		checkParam(csvReader,classificationConfig);
		return new OpenCSVIterator<T>(csvReader, classificationConfig);
	}

	
	private static void checkParam(Config config,List<ClassClassificationConfig> classificationConfigList) {
		assert config!=null;
		assert classificationConfigList!=null;
	}
	
	private static void checkParam(CSVReader  csvReader,ClassClassificationConfig classificationConfigList) {
		assert csvReader!=null;
		assert classificationConfigList!=null;
	}
	
}
