package com.fileobj.exception;

/**
 * Application error codes
 * 
 * @author rdhabal
 *
 */
public class ErrorCodeEnum {

	public static final BaseError APPLICATION_ERROR_20001 = new FileObjError("APPLICATION_ERROR_20001","Application Validation error.","400"); // parameter is not as per expecation
	public static final BaseError APPLICATION_ERROR_20002 = new FileObjError("APPLICATION_ERROR_20002","Application Uninitialized error.","500"); // Object is null or not initialized
	public static final BaseError APPLICATION_ERROR_20003 = new FileObjError("APPLICATION_ERROR_20003","Data is corrupted","500"); //IO Exception
	public static final BaseError APPLICATION_ERROR_20004 = new FileObjError("APPLICATION_ERROR_20004","System resource access error.","404"); //Object was not found
	public static final BaseError APPLICATION_ERROR_20005 = new FileObjError("APPLICATION_ERROR_20005","System can not create entity.","422"); //Create Exception
	public static final BaseError APPLICATION_ERROR_20006 = new FileObjError("APPLICATION_ERROR_20006","Request parameter is not valid","400"); //Bad Request
	
}
