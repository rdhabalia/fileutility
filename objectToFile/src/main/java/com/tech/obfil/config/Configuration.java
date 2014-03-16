package com.tech.obfil.config;

import com.tech.obfil.distributor.ChunkGenerator;
import com.tech.obfil.distributor.DefaultChunkGenerator;
import com.tech.obfil.enums.FileEnum;
import com.tech.obfil.feeder.DefaultTemplateFeeder;

/**
 * 
 * @author rdhabalia
 *
 */
public class Configuration {

	FileConfiguration file;
	Class feeder = DefaultTemplateFeeder.class;
	static int postFix=0;
	ChunkGenerator generator;
	static int totalAsyncThread = 5;
	String processingType = FileEnum.SYNC;

	/**
	 * 
	 * @param fileConfig: File Directory location and file name.
	 */
	public Configuration(FileConfiguration fileConfig){
		file = fileConfig;
		this.generator = new DefaultChunkGenerator();
	}
	
	
	public Configuration(FileConfiguration fileConfig,ChunkGenerator generator) {
		this.file = fileConfig;
		this.generator = generator;
	}


	public FileConfiguration getFile() {
		return file;
	}

	public void setFile(FileConfiguration file) {
		this.file = file;
	}


	public Class getFeeder() {
		return feeder;
	}


	public void setFeeder(Class feeder) {
		this.feeder = feeder;
	}
	
	public static int getPostFix(){
		return postFix++;
	}


	public static void setPostFix(int postFix) {
		Configuration.postFix = postFix;
	}


	public ChunkGenerator getGenerator() {
		return generator;
	}


	public void setGenerator(ChunkGenerator generator) {
		this.generator = generator;
	}


	public static int getTotalAsyncThread() {
		return totalAsyncThread;
	}


	public static void setTotalAsyncThread(int totalAsyncThread) {
		Configuration.totalAsyncThread = totalAsyncThread;
	}


	public String getProcessingType() {
		return processingType;
	}


	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}
	
}
