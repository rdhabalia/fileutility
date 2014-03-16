package com.tech.obfil.formatter;

import java.util.ArrayList;
import java.util.List;

import com.tech.obfil.bean.Element;
import com.tech.obfil.bean.Text;

/**
 * 
 * @author rdhabalia
 *
 */
public class DefaultTextFileTemplate implements Template{

	String name;
	List<Element> element;
	
	public DefaultTextFileTemplate(String name){
		this.name = name;
		this.element = new ArrayList<Element>();
	}
	
	public void appendElement(Element sequence) {
		element.add(sequence);
	}

	public String getFormat() {
		
		StringBuffer format = new StringBuffer();
		for(Element sequence: element){
			
			if(sequence instanceof Text){
				format.append(sequence.getValue());
			}else{
				format.append("{");
				format.append(sequence.getValue());
				format.append("}");
			}
		}
		
		return format.toString();
	}

	public List<Element> getElement() {
		return element;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
