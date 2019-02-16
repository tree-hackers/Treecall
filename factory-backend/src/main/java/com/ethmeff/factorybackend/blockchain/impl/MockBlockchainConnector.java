package com.ethmeff.factorybackend.blockchain.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.blockchain.BCConnector;
import com.ethmeff.factorybackend.model.Part;

@Component
public class MockBlockchainConnector implements BCConnector {

	private static final String CONTRACT_ADDRESS = "0x1233425435356546456";

	@Override
	public String deploy() {
		return CONTRACT_ADDRESS;
	}

	@Override
	public void addParts(List<Part> parts) {
		for (Part part : parts) {
			part.setContractAddress(CONTRACT_ADDRESS);
		}
	}

	@Override
	public Part setBroken(String uuid) {
		return new Part("Klimaanlage", 1, CONTRACT_ADDRESS, UUID.randomUUID().toString(), true);
	}

	@Override
	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts) {
	}

}
