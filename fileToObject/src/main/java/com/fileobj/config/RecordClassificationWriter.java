package com.fileobj.config;
/**
 * @author rdhabal
 *
 */
import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.classifier.RecordClassifier;
import com.fileobj.distributor.FileDistributor;
import com.fileobj.distributor.InMemoryRecordDistributor;
import com.fileobj.distributor.RecordDistributor;
import com.fileobj.enums.FileEnum;
import com.fileobj.exception.BaseAppException;

public class RecordClassificationWriter {

	private RecordClassifier recordClassifier;
	private FileDistributor fileDistributor;
	private ClassClassificationConfig classificationConfig;
	
	public RecordClassificationWriter(RecordClassifier classifier, int totalRecordsPerFile, String outputDir, String mimeType){
		recordClassifier = classifier;
		fileDistributor = new RecordDistributor(totalRecordsPerFile, classifier.getClassifierName()+FileEnum.nameSeparator+mimeType, outputDir, true);
	}
	

	public RecordClassificationWriter(ClassClassificationConfig classificationConfig) {
		recordClassifier = classificationConfig.getClassifier();
		fileDistributor = new InMemoryRecordDistributor(classificationConfig.getRecordsPerFile());
		this.classificationConfig = classificationConfig;
		
	}

	public boolean doesPatternMatch(String data) {
		return recordClassifier.doesPatternMatch(data);
	}

	public boolean write(String data) throws BaseAppException {
		return fileDistributor.writeData(data,false,false);
	}

	public void close() throws BaseAppException {
		fileDistributor.close();
	}
	
	
	public RecordClassifier getRecordClassifier() {
		return recordClassifier;
	}

	public void setRecordClassifier(RecordClassifier recordClassifier) {
		this.recordClassifier = recordClassifier;
	}

	public FileDistributor getFileDistributor() {
		return fileDistributor;
	}

	public void setFileDistributor(FileDistributor fileDistributor) {
		this.fileDistributor = fileDistributor;
	}

	
	public ClassClassificationConfig getClassificationConfig() {
		return classificationConfig;
	}

	public void setClassificationConfig(
			ClassClassificationConfig classificationConfig) {
		this.classificationConfig = classificationConfig;
	}


}
