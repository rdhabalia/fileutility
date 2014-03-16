package com.fileobj.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * Simple record converted from the raw string to pojo
 * 
 * @author rdhabal
 *
 */
public class SimpleRecord implements Record{

	public List<String> columnSet;
	
	public List<String> getColumnSet(){
		if(columnSet==null){
			columnSet = new ArrayList<String>();
		}
		return columnSet;
	}
	
}
