package com.tech.obfil.test;

import com.tech.obfil.annotation.Column;
import com.tech.obfil.bean.Record;

public class FileDetail implements Record{

	@Column(id=0)
	public String recordType;
	@Column(id=1)
	public String packageIdentifier;
	@Column(id=2)
	public String packageIdentifierType;
	@Column(id=3)
	public String shipDateBegin;
	@Column(id=4)
	public String shipDateEnd;
	@Column(id=5)
	public String accountNumber;
	@Column(id=6)
	public String destinationCountryCode;
	@Column(id=7)
	public String partnerTransactionId;
	
	public FileDetail(String recordType, String packageIdentifier,
			String packageIdentifierType, String shipDateBegin,
			String shipDateEnd, String accountNumber,
			String destinationCountryCode, String partnerTransactionId) {
		super();
		this.recordType = recordType;
		this.packageIdentifier = packageIdentifier;
		this.packageIdentifierType = packageIdentifierType;
		this.shipDateBegin = shipDateBegin;
		this.shipDateEnd = shipDateEnd;
		this.accountNumber = accountNumber;
		this.destinationCountryCode = destinationCountryCode;
		this.partnerTransactionId = partnerTransactionId;
	}
	
	
	
}
