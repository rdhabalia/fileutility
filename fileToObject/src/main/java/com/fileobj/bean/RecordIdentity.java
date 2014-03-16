package com.fileobj.bean;

import com.fileobj.classifier.ClassClassificationConfig;

/**
 * It stores resulted classification record and its tempalte
 * @author rdhabal
 *
 * @param <T>
 */
public class RecordIdentity<T> {

	private ClassClassificationConfig<T> classification;
	private T record;
	
	public RecordIdentity(){}
	
	public RecordIdentity(ClassClassificationConfig<T> classification,T record) {
		super();
		this.classification = classification;
		this.record = record;
	}

	public ClassClassificationConfig<T> getClassification() {
		return classification;
	}

	public void setClassification(ClassClassificationConfig<T> classification) {
		this.classification = classification;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "RecordIdentity [classification=" + classification + ", record="
				+ record + "]";
	}
}
