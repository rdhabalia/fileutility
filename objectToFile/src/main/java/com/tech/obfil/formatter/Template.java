package com.tech.obfil.formatter;

import java.util.List;

import com.tech.obfil.bean.Element;

/**
 * 
 * @author rdhabalia
 *
 *@Class: This class preserve unique pattern for set of data record.
 *
 *i.e.:
 *
 *CompanyRecord~Apache
 *Rajan~Contributor~90~
 *
 *Tempalte:
 *2 templates: 
 *
 *
 */
public interface Template {

	/**
	 * This method appends elements to the template. 
	 * @param element: Element can be TEXT (Static text) or TOKEN(ColumnId).
	 * i.e.:
	 * 
	 * public class Person implements Record{

	@Column(id=0)
	public String firstName;
	@Column(id=1)
	public String lastName;
	@Column(id=2)
	public String GPA;
	
		}
	 * TOKEN: 1,2,3
	 */
	void appendElement(Element element);
	
	/**
	 * Get all appened element
	 * @return List<Element>
	 */
	List<Element> getElement();
	
	/**
	 * Get Text Format of Template
	 * i.e.:
	 * FirstName  LastName  GPA
	 * John~Doe~3.9
	 * 
	 * Foramt: {1}~{2}~{3}
	 * 
	 * @return: Text format  of the Template created by appending the template
	 */
	String getFormat();

	/**
	 * Set name of each template for referenec
	 * @param name
	 */
	void setName(String name);
	/**
	 * Get template name
	 * @return name of template
	 */
	String getName();
	
	
}
