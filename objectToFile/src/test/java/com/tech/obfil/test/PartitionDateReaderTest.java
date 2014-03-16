package com.tech.obfil.test;

import java.util.ArrayList;
import java.util.List;

public class PartitionDateReaderTest {

	public void test(){
		
		
		List<Integer> hostIds = new ArrayList<Integer>();
		hostIds.add(1);
		hostIds.add(0);
//		int hostId = 1;
//		int pageSize = 24;
		
		
//		Calendar c1 = Calendar.getInstance();
//		Calendar c2 = Calendar.getInstance();
//		c1.set(2013,Calendar.JANUARY,01);
//		c2.set(2013,Calendar.JANUARY,15);
//		Date cd1 = c1.getTime();
//		Date cd2 = c2.getTime();
//		DateWindow dateWindow = new DateWindow(cd1,cd2);
//		
//		Map<TrackingKeySetEnum,Object> keyValue = new HashMap<TrackingKeySetEnum,Object>();
//		keyValue.put(TrackingKeySetEnum.CARRIER, 1);
//		QuerySpec credential = new TrackingQuerySpec();
//		credential.addQuerySpec(keyValue);
//		
//		Config config = null;
//		try {
//			config = new Config(hostId,pageSize,dateWindow,credential);
//		} catch (BaseAppException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		QueryExecutor executor = new TrackingDefaultQueryExecutor();
//		PageReader reader = new TrackingDatePageReader(executor);
//		RecordPaginator paginator = null;
//		try {
//			paginator = new RecordPaginator(reader,config);
//		} catch (BaseAppException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		while(paginator.hasNext()){
//			Page p = paginator.getNextPage();
//			System.out.println((p!=null && p.getContent()!=null) ? p.getContent().size() : 0);
//		}
		
		
	}
	
}
