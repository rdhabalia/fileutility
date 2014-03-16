package com.tech.obfil.exception;


public class FileObjError extends BaseError {

	protected ErrorSeverity m_errorSeverity = ErrorSeverity.ERROR;
    protected String m_errorCode;
    protected String m_errorMsg;
    protected String m_domain = "LSP";
    protected String m_subDomain = "TRACKING";

	  
	public FileObjError(String errorCode,String errorMsg) {
		super(errorCode,errorMsg);
	}
    
	public FileObjError(String errorCode,String errorMsg, String genericErrorCode ) {
		super(errorCode,errorMsg,genericErrorCode);
	}
	
    public FileObjError(ErrorSeverity errorSeverity, String errorCode, String errorMsg, String domain,String subDomain) {
		super(errorSeverity, errorCode, errorMsg,domain,subDomain);
	}
   
}
