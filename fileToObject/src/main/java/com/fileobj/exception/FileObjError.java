package com.fileobj.exception;

/**
 * 
 * @author rdhabal
 *
 */
public class FileObjError extends BaseError {

	protected String errSeverity = "ERROR";
    protected String errCode;
    protected String errMsg;
    protected String errDomain = "FILE-UTILITU";
    protected String errSubDomain = "FILE-TO-OBJECT-CONVERTER";
    
    
	public FileObjError(String errorCode,String errorMsg) {
		super(errorCode,errorMsg);
	}
    
	public FileObjError(String errorCode,String errorMsg, String genericErrorCode ) {
		super(errorCode,errorMsg,genericErrorCode);
	}
	
    public FileObjError(String errorSeverity, String errorCode, String errorMsg, String domain,String subDomain) {
		super(errorSeverity, errorCode, errorMsg,domain,subDomain);
	}
   
}
