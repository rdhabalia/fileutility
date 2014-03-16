package com.fileobj.user;

import com.fileobj.classifier.RecordClassifier;


public class UserCustomRecordClassifier extends RecordClassifier{

	@Override
	public boolean doesPatternMatch(String line) {
		boolean isFind = false;
		for(String pattern: patterns){
			isFind |= line.startsWith(pattern);
		}
		return isFind;
	}


}
