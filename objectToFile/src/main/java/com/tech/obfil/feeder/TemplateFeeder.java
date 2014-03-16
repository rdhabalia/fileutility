package com.tech.obfil.feeder;

import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.config.FileConfiguration;
import com.tech.obfil.exception.BaseAppException;
import com.tech.obfil.formatter.Template;

/**
 * 
 * @author rdhabalia
 *
 */
public interface TemplateFeeder {

	/**
	 * Start will initialize resources:
	 * i.e.: It will create Output directory and File where records will be written
	 * @param FileConfiguration
	 */
	void start(FileConfiguration fileConfiguration) throws BaseAppException;

	/**
	 * Start feeding data into Template
	 * @param template
	 * @param records
	 * @throws ProcessingException
	 */
	void feed(Template template,List<Record> records) throws BaseAppException;
	
	
}
