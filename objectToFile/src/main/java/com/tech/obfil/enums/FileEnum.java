package com.tech.obfil.enums;

public class FileEnum {

	/**
	 * This starts process in async mode and all processes are independent and they don't wait for any one.
	 * It may cause memory issue because it allows all chunks to load at the same time. 
	 */
	public static final String ASYNC = "ASYNC_FUTURE_PROCESS";
	
	public static final String SYNC = "SYNC";
	
	
}
