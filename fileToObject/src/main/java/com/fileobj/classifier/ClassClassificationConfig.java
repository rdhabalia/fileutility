package com.fileobj.classifier;

/**
 * 
 * It stores template and cofig how to convert given raw string to pojo
 * 
 * @author rdhabal
 *
 */
public class ClassClassificationConfig<T> implements Comparable<ClassClassificationConfig<T>>{

	private RecordClassifier classifier;
	private RecordBreaker recordBreaker = new RecordBreakerImpl();
	private Class<T> className;
	private int recordsPerFile;
	private boolean isReturnEmptyField;
	
	public RecordClassifier getClassifier() {
		return classifier;
	}
	
	public void setClassifier(RecordClassifier classifier) {
		this.classifier = classifier;
	}
	
	public Class<T> getClassName() {
		return className;
	}
	
	public void setClassName(Class<T> className) {
		this.className = className;
	}

	public int getRecordsPerFile() {
		return recordsPerFile;
	}

	public void setRecordsPerFile(int recordsPerFile) {
		this.recordsPerFile = recordsPerFile;
	}

	public boolean isReturnEmptyField() {
		return isReturnEmptyField;
	}

	public void setReturnEmptyField(boolean isReturnEmptyField) {
		this.isReturnEmptyField = isReturnEmptyField;
	}

	public RecordBreaker getRecordBreaker() {
		return recordBreaker;
	}

	public void setRecordBreaker(RecordBreaker recordBreaker) {
		this.recordBreaker = recordBreaker;
	}


	public int compareTo(ClassClassificationConfig<T> o) {

		if(classifier!=null){
			if(classifier.isMetaData()){
				return 0;
			}
		}
		
		return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.getSimpleName().hashCode());
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
		ClassClassificationConfig<T> other = (ClassClassificationConfig<T>) obj;
		if (className == null) {
			if (other.className != null){
				return false;}
		} else if (!className.getSimpleName().equals(other.className.getSimpleName())){
			return false;}
		return true;
	}

	@Override
	public String toString() {
		return "ClassClassificationConfig [classifier=" + classifier
				+ ", recordBreaker=" + recordBreaker + ", className="
				+ className + ", recordsPerFile=" + recordsPerFile
				+ ", isReturnEmptyField=" + isReturnEmptyField + "]";
	}


	
}
