package com.tech.obfil.test;

import com.tech.obfil.annotation.Column;
import com.tech.obfil.bean.Record;

public class FileRecord implements Record{

	@Column(id=0)
	public String trackingNumber;
	@Column(id=1)
	public String sourceZip;
	@Column(id=2)
	public String destinationZip;
	@Column(id=3)
	public String status;
	@Column(id=4)
	public String leg;
	@Column(id=5)
	public String wareHouse;
	@Column(id=6)
	public String date;
	@Column(id=7)
	public int shipmentStatus;
	
	public FileRecord(String trackingNumber, String sourceZip, String destinationZip) {
		
		this.trackingNumber = trackingNumber;
		this.sourceZip = sourceZip;
		this.destinationZip = destinationZip;
	}

	public FileRecord(String trackingNumber, String sourceZip, String destinationZip,
			String status, String leg, String wareHouse, String date, int shipmentStatus) {

		this.trackingNumber = trackingNumber;
		this.sourceZip = sourceZip;
		this.destinationZip = destinationZip;
		this.status = status;
		this.leg = leg;
		this.wareHouse = wareHouse;
		this.date = date;
		this.shipmentStatus = shipmentStatus;
		
		
	}
	
}
