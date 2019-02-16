package com.ethmeff.factorybackend.blockchain.connector;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.model.Part;

@Component
public interface BCConnector {
	public String deploy() throws Exception;

	public void addParts(List<Part> part) throws Exception;

	public boolean setBroken(String contractAddress, String uuid) throws Exception;

	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts);

	boolean changeOwner(Map<Part, String> parts) throws Exception;
}
