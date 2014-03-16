package com.tech.obfil.config;

/**
 * 
 * @author rdhabalia
 *
 */
public class FileConfiguration {

	String fileLocation;
	String directoryLocation;
	String mimeType;
	int totalRecordsInFile;
	/*
	 * It would be appended after each file name: In order to create unique file on each iteration with defined data.
	 */
	String uniqueDistributePostFixId;
	
	/**
	 * 
	 * @param directoryLocation: Parent directory FULL PATH
	 * @param fileLocation: File name without MIME type: READEME for README.TXT (.TXT will be the MIME)
	 * @param mimeType: MIME type of file: .TXT for README.TXT
	 * @param uniqueDistributePostFixId: Post fix to generate unique file name on each iteration.
	 */
	public FileConfiguration(String directoryLocation, String fileLocation,String mimeType, String uniqueDistributePostFixId, int totalRecordsInFile){
		this.fileLocation = fileLocation;
		this.directoryLocation = directoryLocation;
		this.mimeType = mimeType;
		this.uniqueDistributePostFixId = uniqueDistributePostFixId;
		this.totalRecordsInFile = totalRecordsInFile;
	}
	
	public FileConfiguration(String directoryLocation, String fileLocation,String mimeType, String uniqueDistributePostFixId){
		this.fileLocation = fileLocation;
		this.directoryLocation = directoryLocation;
		this.mimeType = mimeType;
		this.uniqueDistributePostFixId = uniqueDistributePostFixId;
	}
	
	public FileConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getDirectoryLocation() {
		return directoryLocation;
	}
	public void setDirectoryLocation(String directoryLocation) {
		this.directoryLocation = directoryLocation;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getUniqueDistributePostFixId() {
		return uniqueDistributePostFixId;
	}

	public void setUniqueDistributePostFixId(String uniqueDistributePostFixId) {
		this.uniqueDistributePostFixId = uniqueDistributePostFixId;
	}

	public int getTotalRecordsInFile() {
		return totalRecordsInFile;
	}

	public void setTotalRecordsInFile(int totalRecordsInFile) {
		this.totalRecordsInFile = totalRecordsInFile;
	}
	
	public String toString(){
		
		StringBuilder msg = new StringBuilder();
		
		msg.append("File Location: ");
		msg.append(this.fileLocation);
		msg.append(",");
		
		msg.append("Directory Location : ");
		msg.append(this.directoryLocation);
		msg.append(",");

		return msg.toString();

	}
	
}
