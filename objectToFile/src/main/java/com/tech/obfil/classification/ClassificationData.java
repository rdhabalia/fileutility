package com.tech.obfil.classification;

import java.util.List;

import com.tech.obfil.bean.Record;
import com.tech.obfil.formatter.Template;

/**
 * 
 * @author rdhabalia
 *
 */
public class ClassificationData {

	
	Template template;
	List<Record> records;
	boolean isMetaData = false;
	int totalRecordPerFile;
	
	/**
	 * 
	 * @param template: Template of record 
	 * @param records: List of data
	 * @param isMetaData: is Static data (Header and Footer)
	 * @param totalRecordPerFile: Total data record per file
	 * 
	 * i.e.:
	 * Person.TXT
	 * University~SJSU~GPA
	 * Rajan~Dhabalia~3.9
	 * Michel~Scolfield~4.0
	 * Sata~Tancredi~3.7
	 * :
	 * :
	 * :
	 * N Records
	 * 
	 * Template: TOKEN1~TOKEN2~TOKEN3
	 * Records: Data: List<Records>
	 * isMetaData: static data is metaData (Header and Footer) i.e.: "University~SJSU_GPA"
	 * totalRecordPerFile: Each file should have only 3 people..
	 * 
	 */
	public ClassificationData(Template template, List<Record> records, boolean isMetaData, int totalRecordPerFile){
		this.template = template;
		this.records = records;
		this.totalRecordPerFile = totalRecordPerFile;
		this.isMetaData = isMetaData;
		
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public boolean isMetaData() {
		return isMetaData;
	}

	public void setMetaData(boolean isMetaData) {
		this.isMetaData = isMetaData;
	}

	public int getTotalRecordPerFile() {
		return totalRecordPerFile;
	}

	public void setTotalRecordPerFile(int totalRecordPerFile) {
		this.totalRecordPerFile = totalRecordPerFile;
	}
}
