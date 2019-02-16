package com.ethmeff.factorybackend.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubPart> subParts = Arrays.asList(new SubPart("123123", UUID.randomUUID()));

	public Part() {
	}

	public Part(String name, BigInteger batch, String contractAddress, String partId, Boolean isBroken) {
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

	public List<SubPart> getSubParts() {
		return subParts;
	}

	public List<String> getSubPartListAsString() {
		if (!subParts.isEmpty()) {
			List<String> result = new ArrayList<>();
			for (SubPart subPart : subParts) {
				result.add(subPart.toString());
			}
			return result;
		} else {
			return Collections.<String>emptyList();
		}
	}

	public void setSubParts(List<SubPart> subParts) {
		this.subParts = subParts;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", batch=" + batch + ", contractAddress=" + contractAddress
				+ ", partId=" + partId + ", isBroken=" + isBroken + ", subParts=" + subParts + "]";
	}
}
