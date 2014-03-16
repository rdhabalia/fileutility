package com.fileobj.enums;

/**
 * 
 * @author rdhabal
 *
 */
public class FileEnum {

	/**
	 * This starts process in async mode and all processes are independent and they don't wait for any one.
	 * It may cause memory issue because it allows all chunks to load at the same time. 
	 */
	public final static long INFINITE_RECORD = 10000000000L; 
	public final static String nameSeparator = ".";
	public static final int FINITE_BIG_NUMBER = 1000000;
	
	
}
