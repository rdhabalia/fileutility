package com.tech.obfil.exception;

/**
 * A base class for all checked exceptions in the application.
 * 
 */
public class BaseAppException extends Exception {

    static final long serialVersionUID = -5829545098534135052L;

//    /**
//     * the message of the BaseAppException.
//     */

    private BaseError error;
    
    protected boolean isRetriable;
    
    protected String referenceId;

	/**
     * A public constructor for BaseAppException containing no arguments.
     *  
     */
    public BaseAppException() {
    }

    /**
     * A public constructor for BaseAppException specifying exception message.
     * <p>
     * 
     * @param msg
     *            exception message.
     *  
     */

    public BaseAppException(BaseError error) {
      super();
      this.error = error;
    }
    
    
    public BaseAppException(BaseError error, String msg) {
        super(msg);
        this.error = error;
        this.referenceId = msg;
        
    }
    
    public BaseAppException(Throwable th, BaseError error) {
        super(th);
        this.error = error;
      }
    
    /**
     * A public constructor of <code>BaseAppException</code> containing
     * message and root cause (as <code>Throwable</code>) of the exception.
     * 
     * @param msg
     *            exception message.
     * @param th
     *            Throwable object.
     *  
     */
    public BaseAppException(Throwable th, String msg) {
    	super(th);
    	if(this.getCause()==null)
        this.initCause(th);
        this.referenceId = msg;
        
    }

    

	public BaseAppException( Throwable th, BaseError error, String msg) {
    	super(th);
	if(this.getCause()==null)
        this.initCause(th);
        this.error = error;
        this.referenceId = msg;
    }
    
    /**
     * sets the root cause of the exception. Used for setting Java built in
     * exception in <code>BaseAppException</code>.
     * 
     * @param e
     *            Throwable object.
     *  
     */
    public void setCause(Throwable e) {
        this.initCause(e);
    }

    /*
     * Gets the class name and exception message.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = getClass().getName();
        return s + ": " + error.getErrorMsg();//exceptionMessage;
    }
    

    /*
     * Gets the message of the exception. equivalent to Exception.getMessage().
     * 
     * @see java.lang.Throwable#getMessage()
//     */
    public BaseError getError() {
		return error;
	}

	public void setError(BaseError error) {
		this.error = error;
	}
	
	 public boolean isRetriable() {
			return isRetriable;
		}

	public void setRetriable(boolean isRetriable) {
		this.isRetriable = isRetriable;
	}
	
	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

}