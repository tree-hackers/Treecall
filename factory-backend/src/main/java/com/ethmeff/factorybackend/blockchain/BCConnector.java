package com.ethmeff.factorybackend.blockchain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.model.Part;

@Component
public interface BCConnector {
	public String deploy();

	public void addParts(List<Part> part);

	public Part setBroken(String uuid);

	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts);
}
