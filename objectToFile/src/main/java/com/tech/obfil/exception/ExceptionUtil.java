package com.tech.obfil.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A helper class which is used to for various utility methods in exception
 * handling. All these utility methods are related to exceptions.
 * 
 */
public class ExceptionUtil {

    /**
     * The length of exception trace beyond which it will be truncated
     */
    public static final int EXCEPTION_TRACE_LENGTH = 4000;

    static final String DOT = ".";

    private static final int INIT_BUFFER_SIZE = 1024;

	private static int LOG_SOURCE =0 ;

    /**
     * Gets the stack trace of a <code>BaseAppException</code> in String form.
     * 
     * @param exception
     *            BaseAppException object
     * @return <code>String</code> Returns the detailed message.
     *  
     */
    public static String getDetailedMessage(BaseAppException exception) {
        StringBuilder msg = new StringBuilder(INIT_BUFFER_SIZE);

        if (exception.getMessage() != null) {
            msg.append("Message : ");
            msg.append(exception.getMessage());
            msg.append("\n");
        }

        msg.append("Exception Stack Trace\n");
        try {
            StringWriter sw = new StringWriter(INIT_BUFFER_SIZE);
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            msg.append(sw.toString());
            sw.close();
            pw.close();
        } catch (Exception e) {
            msg.append(exception.toString());
        }
        Throwable rootCause = exception.getCause();
        if (rootCause != null) {
            msg.append("\n Root Exception Stack Trace : ");
            msg.append(rootCause.toString());
            msg.append("\n");
            try {
                StringWriter sw = new StringWriter(INIT_BUFFER_SIZE);
                PrintWriter pw = new PrintWriter(sw);
                rootCause.printStackTrace(pw);
                msg.append(sw.toString());
                sw.close();
                pw.close();
            } catch (Exception e) {
//                msg.append(rootCause.toString());
            }
        }
        
        msg.append("Message: ");
        msg.append(exception.getError().getErrorMsg());
        return msg.toString();
        
    }

    /**
     * Gets the stack trace of a <code>Throwable</code> in String form.
     * 
     * @param a
     *            Throwable object.
     * @return <code>String</code> Returns the message as String.
     *  
     */
    public static String getDetailedMessage(Throwable a) {
        StringBuilder msg = new StringBuilder();

        msg.append("Message : ");
        msg.append(a.getMessage());
        msg.append("\n");
        msg.append("Exception Stack Trace\n");
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            a.printStackTrace(pw);
            msg.append(sw.toString());
            sw.close();
        } catch (Exception e) {
            msg.append(a.toString());
        }
        String ret = msg.toString();
        msg = null;
        return ret;
    }

    /**
     * Gets the name of class based on passed <code>Throwable</code> instance.
     * 
     * @param e
     *            Throwable object for which class name needs to be find out.
     * @return the name of the class.
     *  
     */
    public static String getClassName(Throwable e) {
        String className = e.getClass().getName();
        return className;
    }

    public static Class getClass(String className){
    	
    	Class clazz = null;
    	
    	try {
			clazz  = Class.forName(className);
	    } catch (Throwable e) {}
		return clazz;
    }
    
    /**
     * Gets the detailed message of a <code>BaseAppException</code> including
     * stack trace, userId and errorId information in String form. This detailed
     * message is used as a stack trace for log file as well as database.
     * 
     * @param exp
     *            BaseAppException object
     * @param userId
     *            the user for which exception occured.
     * @return the detailed message.
     *  
     */
    public static String getExceptionLog(Throwable exp) {
        String errorId = getErrorCode(exp);
        String referenceId = null;
        String detailedMessage = null;
        if (exp instanceof BaseAppException) {
            BaseAppException ie = (BaseAppException) exp;
            detailedMessage = getDetailedMessage(ie);
            referenceId = ((BaseAppException) exp).getReferenceId();
        } else {
            detailedMessage = getDetailedMessage(exp);
        }

        StringBuilder lBuffer = new StringBuilder(INIT_BUFFER_SIZE);
        String msg = null;

        if(errorId!=null){
            lBuffer.append("ERRORID :");
            lBuffer.append(errorId);
            lBuffer.append("\n");
        }
        
        if(referenceId!=null){
        	lBuffer.append("RequestId : ");
            lBuffer.append(referenceId);
            lBuffer.append("\n");
        }
        

        lBuffer.append("EXCEPTION MESSAGE :");
        lBuffer.append(detailedMessage);
        lBuffer.append("\n");
        lBuffer.append("--------------------------------------------------------------------------------------\n");

        msg = lBuffer.toString();
        return msg;
    }


    public static String getErrorCode(Throwable th) {
		
    	String code = null;
    	if(th instanceof BaseAppException) {
    		BaseError error = ((BaseAppException) th).getError();
    		if(error!=null){
    			code = error.getErrorCode();
    		}
    	}
    	return code;
	}
    
    public static void logException(Throwable th, LogLevel level) {
    	
    	logException(th,level,null);
    }
    
    public static void logException(Throwable th, LogLevel level,String message) {
    	
    	String className = getClassName(th);
    	Class clazz = getClass(className);
    	
    	if(th instanceof BaseAppException){
    		BaseError error = ((BaseAppException)th).getError();
    		if(error!=null){
    			StringBuilder msg = new StringBuilder(INIT_BUFFER_SIZE);
    			msg.append(message);
    			msg.append(",[ErrorCode : ");
    			msg.append(error.getErrorCode());
    			msg.append("], [ErrorMessage: ");
    			msg.append(error.getErrorMsg());
    			msg.append("]");
    			message = msg.toString();
    		}
    	}
    	
    	if(clazz!=null){
    		if(message!=null){
    			log(clazz,className,getDetailedMessage(th)+ " AppMessage: "+message,level,th);
    		}else{
    			log(clazz,className,getDetailedMessage(th),level,th);
    		}
    	}
    }
    
    public static void logException(Class clazz, Throwable th, LogLevel level) {
    	log(clazz,clazz.getName(),getDetailedMessage(th),level,getErrorCode(th),th);
    }
    
	public static void logException(Class clazz, String mehod ,Throwable th, String message,  LogLevel level) {
    	
    	log(clazz,mehod,getDetailedMessage(th)+", Message="+message,level,getErrorCode(th),th);
    }
    
	public static void logException(Class clazz, String mehod ,BaseAppException a, String message,  LogLevel level) {
    	log(clazz,mehod,getDetailedMessage(a,level)+", Message="+message,level,getErrorCode(a),a);
    }
    
    public static void logException(Class clazz, String mehod ,BaseAppException a, LogLevel level) {
    	log(clazz,mehod,getDetailedMessage(a,level),level,getErrorCode(a),a);
    }
    
    public static void logException(Class clazz, String mehod ,Throwable th,  LogLevel level) {
    	
    	log(clazz,mehod,getDetailedMessage(th),level,getErrorCode(th),th);
    }
    
  
    private static void log(Class clazz, String method, String message, LogLevel level, String code, Throwable th) {
    	
    	if(code!=null){
    		method += method+"."+code; 
    	}
    	log(clazz, method , message, level,th);
    }
    
    private static void log(Class clazz, String method, String message, LogLevel level,Throwable th) {
    	
    	Logger logger = Logger.getLogger("logger");
    	logger.log(Level.ALL,message, th);

	}
    
    private static String getDetailedMessage(BaseAppException a, LogLevel level) {
    	return getDetailedMessage(a);
	}
    
}
