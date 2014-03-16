package com.tech.obfil.exception;


public class BaseError implements Cloneable {
	  
    protected ErrorSeverity m_errorSeverity = ErrorSeverity.ERROR;
    protected String m_errorCode;
    protected String m_genericErrorCode;
	protected String m_errorMsg;
    protected String m_domain = "LSP";
    protected String m_subDomain = "TRACKING";

    public BaseError(String errorMsg) {
        m_errorMsg = errorMsg;
    }

    public BaseError(String errorCode,String errorMsg) {
        m_errorCode = errorCode;
        m_errorMsg = errorMsg;
    }
    
    public BaseError(String errorCode,String errorMsg, String genericErrorCode) {
        m_errorCode = errorCode;
        m_errorMsg = errorMsg;
        m_genericErrorCode = genericErrorCode;
    }


    public BaseError(ErrorSeverity errorSeverity,String errorCode,String errorMsg) {
        m_errorCode = errorCode;
        m_errorMsg = errorMsg;
        m_errorSeverity = errorSeverity;
    }

    public BaseError(ErrorSeverity errorSeverity,String errorCode,String errorMsg, String domain, String subDomain) {
        m_errorCode = errorCode;
        m_errorMsg = errorMsg;
        m_errorSeverity = errorSeverity;
        m_domain = domain;
        m_subDomain = subDomain;
    }

    public String getDomain() {
        return m_domain;
    }

    public void setDomain(String domain) {
        m_domain = domain;
    }

    public String getSubDomain() {
        return m_subDomain;
    }

    public void setSubDomain(String subDomain) {
        m_subDomain = subDomain;
    }


    public String getErrorMsg() {
        return m_errorMsg;
    }

    public ErrorSeverity getErrorSeverity() {
        return m_errorSeverity;
    }

    public void setErrorMsg(String errorMsg) {
        m_errorMsg = errorMsg;
    }
    
    public String getGenericErrorCode() {
		return m_genericErrorCode;
	}

	public void setGenericErrorCode(String m_genericErrorCodem_) {
		m_genericErrorCode = m_genericErrorCodem_;
	}
	
    public void appendToErrorMsg(String additionalMsg) {
        if ( this.m_errorMsg != null ) {
            this.m_errorMsg += ". "+additionalMsg;          
        } else {
            this.m_errorMsg = additionalMsg;
        }
    }

    public void setErrorSeverity(ErrorSeverity errorSeverity) {
        m_errorSeverity = errorSeverity;
    }

    public String getErrorCode() {
        return m_errorCode;
    }

    public void setErrorCode(String errorCode) {
        m_errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "BaseError: {class="+this.getClass().getName()+", severity="+m_errorSeverity+", code="+m_errorCode+",msg="+m_errorMsg+"}";
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
        + ((m_errorCode == null) ? 0 : m_errorCode.hashCode());
        result = prime * result
        + ((m_errorMsg == null) ? 0 : m_errorMsg.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseError other = (BaseError) obj;
        if (m_errorCode == null) {
            if (other.m_errorCode != null)
                return false;
        } else if (!m_errorCode.equals(other.m_errorCode))
            return false;
        if (m_errorMsg == null) {
            if (other.m_errorMsg != null)
                return false;
        } else if (!m_errorMsg.equals(other.m_errorMsg))
            return false;
        return true;
    }


    public BaseError clone() {
        BaseError clonned = null;
        try {
            clonned = (BaseError)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported for "+super.getClass());
        }
        clonned.m_errorSeverity = m_errorSeverity;
        clonned.m_errorCode = m_errorCode;
        clonned.m_errorMsg = m_errorMsg;
        clonned.m_domain = m_domain;
        clonned.m_subDomain = m_subDomain;
        return clonned;
    }
}
