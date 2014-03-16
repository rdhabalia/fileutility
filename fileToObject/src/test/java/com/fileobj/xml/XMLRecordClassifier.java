package com.fileobj.xml;

import com.fileobj.classifier.RecordClassifier;

public class XMLRecordClassifier extends RecordClassifier{

	@Override
	public boolean doesPatternMatch(String line) {
		boolean isFind = false;
		for(String pattern: patterns){
			isFind |= line.startsWith(pattern);
		}
		return isFind;
	}


}
