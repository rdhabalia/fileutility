package com.tech.obfil.feeder;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import com.tech.obfil.annotation.Column;
import com.tech.obfil.bean.Record;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.exception.ErrorCodeEnum;
import com.tech.obfil.formatter.Template;
import com.tech.obfil.writer.FileRecordWriter;
import com.tech.obfil.writer.RecordWriter;

/**
 * 
 * @author rdhabalia
 *
 */
public class DefaultTemplateFeeder implements TemplateFeeder{

	
	RecordWriter<String> writer = null;
	
	public DefaultTemplateFeeder(){
		
	}
	
	public void start(FileConfiguration fileConfig) {
		
		writer = new FileRecordWriter(fileConfig.getDirectoryLocation(),fileConfig.getFileLocation());
	}

	public void feed(Template template, List<Record> records) throws BaseAppException {
		
		if(writer==null){
			throw new BaseAppException(ErrorCodeEnum.APPLICATION_ERROR_20001, "File has not been initialized");
		}
		
		String format = template.getFormat();

		if(records!=null && !records.isEmpty()){
			MessageFormat formatter = new MessageFormat(format);
			for(Record record: records){
				String[] values = getValues(record);
				StringBuffer buffer = new StringBuffer();
			    formatter.format(values, buffer, null);
			    String textRecord = buffer.toString();
			    appendRecord(textRecord);
			}
		}else{
			appendRecord(format);
		}
		
		
		
		
	}

	private String[] getValues(Record record) throws BaseAppException {
		
		Field[] fieldData = record.getClass().getDeclaredFields();
		String[] rawValues = new String[fieldData.length];
		for(int i=0;i<fieldData.length;i++){
			Field field = fieldData[i];
			if(field.isAnnotationPresent(Column.class)){
				Column column = field.getAnnotation(Column.class);
        		if(column!=null){
        			int id = column.id();
        			if(id < fieldData.length){
        				try {
        					Object val = field.get(record);
        					String value = "";
        					if(val!=null){
        						value = val.toString();
        					}
        					rawValues[id] =  value;
						} catch (IllegalArgumentException e) {
							throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20001);
						} catch (IllegalAccessException e) {
							throw new BaseAppException(e,ErrorCodeEnum.APPLICATION_ERROR_20001);
						}
        			}
        		}
			}
			
		}
		
		return rawValues;
	}

	private void appendRecord(String textRecord) throws BaseAppException {
		writer.writeData(textRecord, true, true);
	}

}
