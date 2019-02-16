package com.ethmeff.factorybackend.blockchain.connector.impl.eth;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.contract.FactoryParts;
import com.ethmeff.factorybackend.model.Part;

@Component
public class EthBCConnector implements BCConnector {

	private static final BigInteger GAS_PRICE = new BigInteger("1");

	@Autowired
	private Web3j web3j;

	@Value("${privatekey}")
	private String privateKey;

	@Value("${gaslimit}")
	private BigInteger gasLimit;

	@Override
	public String deploy() throws Exception {
		FactoryParts contract = FactoryParts.deploy(web3j, getCredentials(), GAS_PRICE, gasLimit).send();
		return contract.getContractAddress();
	}

	@Override
	public void addParts(List<Part> parts) throws Exception {
		String contractAddress = "";
		FactoryParts foundParts = null;
		for (Part part : parts) {
			String savedContractAddress = part.getContractAddress();
			if (!contractAddress.equalsIgnoreCase(savedContractAddress)) {
				contractAddress = savedContractAddress;
				foundParts = findFactoryPartsByAddress(contractAddress);
			}
			foundParts.storePart(part.getPartId(), part.getName(), part.getBatch(), part.getSubPartListAsString());
		}
	}

	@Override
	public Part setBroken(String contractAddress, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSubParts(String contractAddress, String uuidParentPart, Part... parts) {
		// TODO Auto-generated method stub

	}

	private Credentials getCredentials() {
		return Credentials.create(privateKey);
	}

	private FactoryParts findFactoryPartsByAddress(String contractAddress) {
		return FactoryParts.load(contractAddress, web3j, getCredentials(), GAS_PRICE, gasLimit);
	}

}
