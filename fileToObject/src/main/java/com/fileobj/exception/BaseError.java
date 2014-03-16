package com.fileobj.exception;

/**
 * 
 * @author rdhabal
 *
 */
public class BaseError implements Cloneable {
	  
    protected String errSeverity = "ERROR";
    protected String errCode;
    protected String genericErrCode;
	protected String errMsg;
    protected String errDomain = "FILE-UTILITY";
    protected String errSubDomain = "FILE-TO-OBJECT-CONVERTER";

    public BaseError(String errorMsg, String errorCode, String errorMsg2, String domain, String subDomain) {
        errMsg = errorMsg;
    }

    public BaseError(String errorCode,String errorMsg) {
        errCode = errorCode;
        errMsg = errorMsg;
    }
    
    public BaseError(String errorCode,String errorMsg, String genericErrorCode) {
        errCode = errorCode;
        errMsg = errorMsg;
        genericErrCode = genericErrorCode;
    }


    public String getDomain() {
        return errDomain;
    }

    public void setDomain(String domain) {
        errDomain = domain;
    }

    public String getSubDomain() {
        return errSubDomain;
    }

    public void setSubDomain(String subDomain) {
        errSubDomain = subDomain;
    }


    public String getErrorMsg() {
        return errMsg;
    }


    public void setErrorMsg(String errorMsg) {
        errMsg = errorMsg;
    }
    
    public String getGenericErrorCode() {
		return genericErrCode;
	}

	public void setGenericErrorCode(String m_genericErrorCodem_) {
		genericErrCode = m_genericErrorCodem_;
	}
	
    public void appendToErrorMsg(String additionalMsg) {
        if ( this.errMsg != null ) {
            this.errMsg += ". "+additionalMsg;          
        } else {
            this.errMsg = additionalMsg;
        }
    }


    public String getErrorCode() {
        return errCode;
    }

    public void setErrorCode(String errorCode) {
        errCode = errorCode;
    }

    @Override
    public String toString() {
        return "BaseError: {class="+this.getClass().getName()+", severity="+errSeverity+", code="+errCode+",msg="+errMsg+"}";
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
        + ((errCode == null) ? 0 : errCode.hashCode());
        result = prime * result
        + ((errMsg == null) ? 0 : errMsg.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;}
        if (obj == null){
            return false;}
        if (getClass() != obj.getClass()){
            return false;}
        BaseError other = (BaseError) obj;
        if (errCode == null) {
            if (other.errCode != null){
                return false;}
        } else if (!errCode.equals(other.errCode)){
            return false;}
        if (errMsg == null) {
            if (other.errMsg != null){
                return false;}
        } else if (!errMsg.equals(other.errMsg)){
            return false;}
        return true;
    }


    public BaseError clone() {
        BaseError clonned = null;
        try {
            clonned = (BaseError)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported for "+super.getClass());
        }
        clonned.errSeverity = errSeverity;
        clonned.errCode = errCode;
        clonned.errMsg = errMsg;
        clonned.errDomain = errDomain;
        clonned.errSubDomain = errSubDomain;
        return clonned;
    }
}
