package com.fileobj.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * It decides if file can be read using Inputstream of from the actual physical location
 * 
 * @author rdhabal
 *
 */
public enum FileReadingType {

	
	LINE_READING("LINE_READING"),
	BUFFER_READING("BUFFER_READING");
	
	private String fileReadingType;
	private static Map<String, FileReadingType> typeMapping;

	private FileReadingType(String status){
		this.fileReadingType = status;
	}
	

	private static void initMapping() {
		typeMapping = new HashMap<String, FileReadingType>();
        for (FileReadingType providerType : values()) {
        	typeMapping.put(providerType.fileReadingType, providerType);
        }
    }

	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("File_READING_TYPE: ");
        sb.append(fileReadingType);
        return sb.toString();
    }
	
	
}
