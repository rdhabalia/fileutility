package com.fileobj.classifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Token generator.
 * <p>
 * <h3>Implement Token generator(RecordBreakerImpl)</h3>
 * 
 * This class is optional class and default behavior of this class would
 * break lines into tokens by considering all list of delimiters.. 
 * 
 * generateTokensForLine(..) method will be implemented method and it generates 
 * tokens from line and stores into list(List<String>tokens)..
 * 
 * <pre>
 * public void generateTokensForLine(String line, boolean isEmptyToken){
 *     // Implement.
 * }
 * </pre>
 * 
 * @author rdhabal
 * 
 */

public abstract class RecordBreaker {

	protected List<String> delimiters;
	
	protected List<String> tokens = new ArrayList<String>();
	
	protected int index = 0;
	
	 /**
     * generate tokens(attributes) from the given line using delimiter that is used to break lines 
     * into tokens.
     * 
     * <pre>
     * delimiters: list of delimiters that are used to create tokens
     * tokens: store all generated tokens into this list 
     * </pre>
     *  
     * @param line raw data or String that should be broken into token.
     * @param isEmptyToken consider empty string as a toekn that present in between two 
     * consecutive delimiters.
     */
	public abstract void generateTokensForLine(String line, boolean isEmptyToken);
	

	public List<String> getDelimiters() {
		if(delimiters==null){
			delimiters = new ArrayList<String>();
		}
		return delimiters;
	}
	
	public boolean hasMoreTokens() {
		boolean hasMoreTokens =  index<tokens.size();
		if(!hasMoreTokens){
			tokens.clear();
			index = 0;
		}
		return hasMoreTokens;
	}

	public String nextToken() {
		return tokens.get(index++);
	}


	@Override
	public String toString() {
		return "RecordBreaker [delimiters=" + delimiters + ", tokens=" + tokens
				+ ", index=" + index + "]";
	}
}
