package com.ethmeff.factorybackend.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.web3j.tuples.generated.Tuple6;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties
public class Part {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigInteger batch;
	private String contractAddress;
	private String partId;
	private Boolean isBroken;
	@ElementCollection
	private List<String> subPartsContracts = Arrays.asList("0x0");
	@ElementCollection
	private List<String> subPartsUUID = Arrays.asList("");

	public Part() {
	}

	public Part(String name, BigInteger batch, String contractAddress, String partId, Boolean isBroken) {
		this.name = name;
		this.batch = batch;
		this.contractAddress = contractAddress;
		this.partId = partId == null || partId.isEmpty() ? UUID.randomUUID().toString() : partId;
		this.isBroken = isBroken;
	}

	public Part(Long id, String partId,
			Tuple6<String, BigInteger, String, Boolean, List<String>, List<String>> foundPart) {
		this.id = id;
		this.name = foundPart.getValue1();
		this.batch = foundPart.getValue2();
		this.isBroken = foundPart.getValue4();
		this.subPartsContracts = foundPart.getValue5();
		this.partId = partId;
		this.subPartsUUID = foundPart.getValue6();

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

	public BigInteger getBatch() {
		return batch;
	}

	public void setBatch(BigInteger batch) {
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

	public List<String> getSubPartsContracts() {
		return subPartsContracts;
	}

	public void setSubPartsContracts(List<String> subPartsContracts) {
		this.subPartsContracts = subPartsContracts;
	}

	public List<String> getSubPartsUUID() {
		return subPartsUUID;
	}

	public void setSubPartsUUID(List<String> subPartsUUID) {
		this.subPartsUUID = subPartsUUID;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", batch=" + batch + ", contractAddress=" + contractAddress
				+ ", partId=" + partId + ", isBroken=" + isBroken + ", subPartsContracts=" + subPartsContracts
				+ ", subPartsUUID=" + subPartsUUID + "]";
	}

}
