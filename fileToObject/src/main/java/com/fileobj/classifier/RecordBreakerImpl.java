package com.fileobj.classifier;

import java.util.ArrayList;
import java.util.List;


/**
 * @author rdhabal
 *
 */
public class RecordBreakerImpl extends RecordBreaker{

	protected CustomStringTokenizer st;
	
	public void generateTokensForLine(String line, boolean isEmptyToken) {

		StringBuilder token = new StringBuilder();

		for(String delimiter: delimiters){
			token.append(delimiter);
		}
	        st = new CustomStringTokenizer(line, token.toString());
                st.setReturnEmptyTokens(isEmptyToken);
		
	}
	
	public boolean hasMoreTokens(){
		return st.hasMoreTokens();
	}
	
	public String nextToken(){
		return st.nextToken();
	}

	public List<String> getDelimiters() {
		if(delimiters==null){
			delimiters = new ArrayList<String>();
		}
		return delimiters;
	}
}
