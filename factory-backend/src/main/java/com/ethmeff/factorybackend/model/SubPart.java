package com.ethmeff.factorybackend.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubPart {

	@Id
	@GeneratedValue
	private Long id;
	private String contractAddress;
	private UUID partId;

	public SubPart() {
	}

	public SubPart(String contractAddress, UUID partId) {
		this.contractAddress = contractAddress;
		this.partId = partId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public UUID getPartId() {
		return partId;
	}

	public void setPartId(UUID partId) {
		this.partId = partId;
	}
}
