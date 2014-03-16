package com.tech.obfil.executor;

import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.feeder.TemplateFeeder;
import com.tech.obfil.formatter.Template;

/**
 * 
 * @author rdhabalia
 * This class processes data and pass it to appropriate Feeder to prepare file 
 * in Synchronous or Asynchronous manner.
 */
public interface FeedProcessor {

	/**
	 * Start processing of template feeding
	 * 
	 * @param template: Template
	 * @param records: Data which will be feeded into template
	 * @param feeder : Feeder which feeds data into template
	 * @param fileConfiguration : File where to write Data
	 */
	void process(Template template, List<Record> records,
			TemplateFeeder feeder, FileConfiguration file) throws BaseAppException;

	/**
	 * 
	 * @param data: ClassificationData combination of Template and data which wil be feeded into Template
	 * @param fileConfiguration: File location and name
	 * @param feeder: Feeder which feeds data into template
	 */
	void process(List<ClassificationData> data, FileConfiguration file,
			TemplateFeeder feeder) throws BaseAppException;
}
