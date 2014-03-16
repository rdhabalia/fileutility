package com.fileobj.iterator.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fileobj.bean.RecordIdentity;
import com.fileobj.bean.SimpleRecord;
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordBreaker;
import com.fileobj.config.Config;
import com.fileobj.config.RecordClassificationWriter;
import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ErrorCodeEnum;
import com.fileobj.feeder.DataFeeder;
import com.fileobj.helper.LineHelper;
import com.fileobj.iterator.FileIterator;
import com.fileobj.iterator.FileIteratorFactory;
import com.fileobj.iterator.FileReader;

/**
 * @author rdhabalia
 *
 */
public class SimpleRecordIterator implements RecordIterator<RecordIdentity>{

	private FileIterator lineIterator = null;
	private List<RecordClassificationWriter> classificationWriters = null;
	 private static final Logger log = Logger.getLogger(SimpleRecordIterator.class);
	
	/**
	 *
	 * @param  config  Common config for file splitting
	 * @param  classificationConfigList Classifier specific config
	 * @throws BaseAppException 
	 * @see         
	 */
	@SuppressWarnings("rawtypes")
	SimpleRecordIterator(Config config, List<ClassClassificationConfig> classificationConfigList) throws BaseAppException {
		
		
		this.classificationWriters = new ArrayList<RecordClassificationWriter>();
		
		try {
			for(ClassClassificationConfig classificationConfig: classificationConfigList){
				RecordClassificationWriter classificationWriter = new RecordClassificationWriter(classificationConfig);
				classificationWriters.add(classificationWriter);
			}
			
			FileReader reader = config.getFileReader();
			lineIterator = FileIteratorFactory.getFileIterator(config.getSrcPath(),config.getFileReadingType(),config.getNewLineDelimiters(),reader);
			
		} catch (IOException e) {
			throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20003,config.getSrcPath());
		}
		
	}
	
	
	
	public boolean hasNext(){
		return lineIterator.hasNext();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RecordIdentity next() throws BaseAppException{
		
		if (lineIterator.hasNext()) {
			String data = (String) lineIterator.next();
			for (RecordClassificationWriter classificationWriter : classificationWriters) {
				if (classificationWriter.doesPatternMatch(data)) {
					ClassClassificationConfig classConfig = classificationWriter.getClassificationConfig();
					List<Object> list = getRecords(classConfig,data);
					if(list!=null && !list.isEmpty()){
						return new RecordIdentity(classificationWriter.getClassificationConfig(),list.get(0));
					}
				}
			}
		}
		return null;
	}
	
	
	
	/****************************************************************** HELPER METHOD *********************************************************************************/
	
	/**
	 * 
	 * @param classificationConfig
	 * @param config
	 * @param rawData
	 * @return
	 * @throws BaseAppException
	 */
	private <T> List<T> getRecords(ClassClassificationConfig<T> classificationConfig, String rawData) throws BaseAppException{
		
		SimpleRecord record = convertToSimpleRecords(rawData,classificationConfig.getRecordBreaker(),classificationConfig.isReturnEmptyField());
		return feedRecords(classificationConfig,record);
		
	}


	private <T> List<T> feedRecords(ClassClassificationConfig<T> classificationConfig, final SimpleRecord record) throws BaseAppException {
		
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
	
	
	private SimpleRecord convertToSimpleRecords(String line, RecordBreaker delimiters, boolean isEmptyToken) throws BaseAppException{

		return getRecordFromRecord(line,delimiters,isEmptyToken);
	}
	
	
	
	protected SimpleRecord getRecordFromRecord(String line, RecordBreaker delimiters, boolean isEmptyToken) throws BaseAppException{
		return LineHelper.getRecordsFromLine(line,delimiters,isEmptyToken);
	}
	
	
}
