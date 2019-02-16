package com.ethmeff.factorybackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int batch;
	private String contractAddress;
	private String partId;
	private Boolean isBroken;

	public Part() {
	}

	public Part(String name, int batch, String contractAddress, String partId, Boolean isBroken) {
		this.name = name;
		this.batch = batch;
		this.contractAddress = contractAddress;
		this.partId = partId;
		this.isBroken = isBroken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public Boolean getIsBroken() {
		return isBroken;
	}

	public void setIsBroken(Boolean isBroken) {
		this.isBroken = isBroken;
	}

}
