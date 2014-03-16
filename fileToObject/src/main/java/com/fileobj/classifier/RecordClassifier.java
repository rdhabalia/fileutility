package com.fileobj.classifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Classifier to distinguish each line of file as a record. 
 * <p>
 * <h3>Implement Custom Classifier</h3>
 * 
 *  
 * It should be extended by Custom Classifier that defines rules
 * to classify line as a record. It has method doesPatternMatch() that defines
 * rules to distinguish record.
 * 
 * <pre>
 * public boolean doesPatternMatch(String line){
 *     // Implement.
 * }
 * </pre>
 * 
 * @author rdhabal
 */

public abstract class RecordClassifier {

	private String classifierName;
	protected List<String> patterns = new ArrayList<String>();
	private boolean isMetaData = false;
	
	 /**
     * Validates lines and checks whether it matches to specific record (i.e. Data, MetaData,..)
     * @param line Raw line that should be matched to rules in order to be record for that classifier.
     * @return boolean true/false The acknowledgement whether line is matched to specific rule or not.
     */
	public abstract boolean doesPatternMatch(String line);

	public String getClassifierName() {
		return classifierName;
	}

	public void setClassifierName(String classifierName) {
		this.classifierName = classifierName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classifierName == null) ? 0 : classifierName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;}
		if (obj == null){
			return false;}
		if (getClass() != obj.getClass()){
			return false;}
		RecordClassifier other = (RecordClassifier) obj;
		if (classifierName == null) {
			if (other.classifierName != null){
				return false;}
		} else if (!classifierName.equals(other.classifierName)){
			return false;}
		return true;
	}

	public List<String> getPatterns() {
		return patterns;
	}

	public boolean isMetaData() {
		return isMetaData;
	}

	public void setMetaData(boolean isMetaData) {
		this.isMetaData = isMetaData;
	}

	@Override
	public String toString() {
		return "RecordClassifier [classifierName=" + classifierName
				+ ", isMetaData=" + isMetaData + "]";
	}
}
