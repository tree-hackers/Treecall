package com.ethmeff.factorybackend.blockchain.connector.impl.mock;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
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
	public List<Part> getAllTokens(List<Part> findAll) {
		if (findAll.isEmpty())
			return Collections.EMPTY_LIST;
		return Arrays.asList(new Part("Klimaanlage", new BigInteger("1"), "", UUID.randomUUID().toString(), false));
	}

}
