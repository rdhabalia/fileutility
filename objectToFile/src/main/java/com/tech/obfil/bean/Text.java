package com.tech.obfil.bean;

/**
 * 
 * @author rdhabalia
 *
 */
public class Text implements Element {

	String text;
	
	public Text(){
		
	}
	
	public Text(String value){
		text = value;
	}
	
	public void setValue(Object value) {
		text = (String) value;
	}

	public String getValue() {
		return text;
	}


}
