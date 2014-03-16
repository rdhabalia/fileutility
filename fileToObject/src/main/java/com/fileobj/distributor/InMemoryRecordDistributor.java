package com.fileobj.distributor;
/**
 * @author rdhabal
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fileobj.exception.BaseAppException;

public class InMemoryRecordDistributor implements FileDistributor{

	
	private long recordsPerFile = 0;
	private int onGoingChunkNumbr = 0;
	private int totalChunk =0;
	List<String> rawData;
	
	public static final String domainName = "FileDistributor";
	
	public InMemoryRecordDistributor(long totalRecordsPerFile) {
		recordsPerFile = totalRecordsPerFile;
	}

	public boolean writeData(Object data, boolean append, boolean doFlush)	throws BaseAppException {

		boolean isFull = false;
		
		if(!isStorageFull()){
			if(data!=null){
				getRawData().add(data.toString());
			}
		}else{
			totalChunk++;
			isFull = true;
		}
		
		return isFull;		
	}


	public void close() throws BaseAppException {
		cleanStorage();
	}

	public int getTotalChunk() {
		return totalChunk;
	}

	
	public List<String> getRawData() {
		if(rawData==null){
			rawData = new ArrayList<String>();
		}
		return rawData;
	}

	private boolean isStorageFull() {
		
		onGoingChunkNumbr++;
		return onGoingChunkNumbr>recordsPerFile;
	}
	
	public void cleanStorage(){
		rawData = null;
		setOnGoingChunkNumbr(0);
		
	}

	public int getOnGoingChunkNumbr() {
		return onGoingChunkNumbr;
	}

	public void setOnGoingChunkNumbr(int onGoingChunkNumbr) {
		this.onGoingChunkNumbr = onGoingChunkNumbr;
	}
	
	
	
}
