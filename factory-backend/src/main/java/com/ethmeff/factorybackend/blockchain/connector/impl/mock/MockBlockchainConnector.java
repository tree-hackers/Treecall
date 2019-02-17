package com.ethmeff.factorybackend.blockchain.connector.impl.mock;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.model.PartTree;

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
	public boolean setBroken(String contractAddress, String uuid) {
		return true;
	}

	@Override
	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts) {
	}

	@Override
	public boolean changeOwner(Map<Part, String> parts) throws Exception {
		return true;
	}

	@Override
	public PartTree getAllOwnParts(Part part) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
