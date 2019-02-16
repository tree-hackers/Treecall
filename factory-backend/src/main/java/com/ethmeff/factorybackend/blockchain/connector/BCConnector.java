package com.ethmeff.factorybackend.blockchain.connector;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.model.Part;

@Component
public interface BCConnector {
	public String deploy() throws Exception;

	public void addParts(List<Part> part) throws Exception;

	public Part setBroken(String contractAddress, String uuid);

	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts);
}
