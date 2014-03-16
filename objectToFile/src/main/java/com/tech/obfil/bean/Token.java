package com.tech.obfil.bean;

/**
 * 
 * @author rdhabalia
 *
 */
public class Token implements Element{

	int columnId;
	
	public Token(){}
	
	public Token(Integer columnId){
		
		this.columnId = columnId;
	}
	
	public void setValue(Object value) {
		this.columnId = (Integer) value;
	}

	public Integer getValue() {
		return columnId;
	}


}
