package com.fileobj.iterator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.omg.CORBA.SystemException;

import com.fileobj.classifier.ClassClassificationConfig;
import com.fileobj.exception.BaseAppException;
import com.fileobj.exception.ExceptionUtil;


/**
 * @author rdhabalia
 *
 */
public class XMLIterator implements FileIterator{

	private XMLEventReader xmler = null;
	private InputStream is = null;
	private Package pkg = null;
	private Map<String,ClassClassificationConfig> classifierMap = null;
	
	
	public XMLIterator(String filePath,Package pkg,List<ClassClassificationConfig> classifiers) throws FileNotFoundException, XMLStreamException{
		
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		FileReader fr = new FileReader(filePath);
        xmler = xmlif.createXMLEventReader(fr);
        this.pkg = pkg;
        this.classifierMap = prepareClassifierMap(classifiers);
		
	}
	
	
	public XMLIterator(InputStream is,Package pkg,List<ClassClassificationConfig> classifiers) throws FileNotFoundException, XMLStreamException{
		
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
        xmler = xmlif.createXMLEventReader(is);
        this.pkg = pkg;
        this.classifierMap = prepareClassifierMap(classifiers);
		
	}
	
	
	public boolean hasNext() {
		return xmler.hasNext();
	}

	public Map<ClassClassificationConfig,List> next() throws SystemException{
		return parse();
	}

	
	public void close() throws IOException{
		if(is!=null)
		is.close();
	}

	/*********************************************  HELPER METHOD  ********************************************/
	
	
	public Map<ClassClassificationConfig,List> parse() {
    	
		
		Map<ClassClassificationConfig,List> result = new HashMap<ClassClassificationConfig,List>();
		
	    	try{

	            // Parse into typed objects
	            JAXBContext ctx = JAXBContext.newInstance(pkg.getName());
	            Unmarshaller um = ctx.createUnmarshaller();
	            
	    		while (xmler.hasNext()) {
	    			
	    			if(xmler.peek() != null && xmler.peek().getEventType()==XMLStreamConstants.START_ELEMENT){
	    				
	    				XMLEvent event = xmler.peek();
	    				
	    				if(event.isStartElement()){
	    					
	    					StartElement st = (StartElement) event;
	    					
	    					String className = st.getName().toString();
	    					
	    					if(classifierMap.containsKey(className)){
	    						
	    						ClassClassificationConfig classConfig = classifierMap.get(className);
	    						Class clazz = classConfig.getClassName();
		            			Object classifierObject = clazz.newInstance();
	    						
	    						Object xmlObject = um.unmarshal(xmler);
	    						
	    						if(xmlObject.getClass().isInstance(classifierObject)){
	    	            			
		            				List l = result.get(classConfig);
		            				if(l==null){
		            					l = new ArrayList();
		            					result.put(classConfig, l);
		            				}
		            				
		            				l.add(xmlObject);
		            				
		            				if(l.size()>=classConfig.getRecordsPerFile()){
		            					return result;
		            				}
		            			}
	    						
	    						
	    					}
	    					
	    				}
	    				
	    			}
	    			
	    			xmler.next();
	    		}
	            

	    	}catch(XMLStreamException th){
	    		ExceptionUtil.logException(th,Level.ALL);
	    		throw new RuntimeException(th.getMessage(),th);
	    	}catch(JAXBException th){
	    		ExceptionUtil.logException(th,Level.ALL);
	    		throw new RuntimeException(th.getMessage(),th);
	    	}catch(Exception th){
	    		ExceptionUtil.logException(th,Level.ALL);
	    		throw new RuntimeException(th.getMessage(),th);
	    	}
	    	
	        return result;
	    }
	
	
	public FileIterator getNewInstance(String path) throws BaseAppException {
		// TODO Auto-generated method stub
		return null;
	}


	public void setFileReader(com.fileobj.iterator.FileReader reader) {
		// TODO Auto-generated method stub
		
	}
	
	
	private Map<String, ClassClassificationConfig> prepareClassifierMap(List<ClassClassificationConfig> classifiers) {
		
		Map<String,ClassClassificationConfig> classifierMap = new HashMap<String,ClassClassificationConfig>(); 
		
		if(classifiers!=null){
			for(ClassClassificationConfig config: classifiers){
				classifierMap.put(config.getClassName().getSimpleName(), config);
			}
		}
		
		return classifierMap;
	}
	
}
