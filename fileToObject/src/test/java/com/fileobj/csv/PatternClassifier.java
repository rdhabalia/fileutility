package com.fileobj.csv;

import com.fileobj.classifier.RecordClassifier;


public class PatternClassifier extends RecordClassifier{

	@Override
	public boolean doesPatternMatch(String line) {
		
		if(line.startsWith("Company_Identifier")){
			return false;
		}
		
		return true;
	}


}
