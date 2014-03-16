package com.tech.obfil.executor;

import java.util.List;

import com.tech.obfil.classification.ClassificationData;
import com.tech.obfil.config.Configuration;
import com.tech.obfil.exception.BaseAppException;

/**
 * 
 * @author rdhabalia
 *
 *	This is a main starter of entire conversion process from Java object to Text file.
 *
 */
public interface BaseStarter {

	/**
	 * It takes DataContent and Configuration such as file name to write data into File.
	 * @param data
	 * @param config
	 * @throws BaseAppException
	 */
	void executeFeedProcessing(List<ClassificationData> data, Configuration config) throws BaseAppException;
}
