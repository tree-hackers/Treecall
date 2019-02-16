package com.ethmeff.factorybackend.blockchain.connector.impl.eth;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

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
			if (!contractAddress.equalsIgnoreCase(savedContractAddress) || savedContractAddress == null
					|| savedContractAddress.isEmpty()) {
				contractAddress = savedContractAddress;
				foundParts = findFactoryPartsByAddress(contractAddress);
			}
			RemoteCall<TransactionReceipt> storePart = foundParts.storePart(part.getPartId(), part.getName(),
					part.getBatch(), part.getSubPartsContracts(), part.getSubPartsUUID());
			String status = storePart.send().getStatus();
			if (!status.equalsIgnoreCase("0x1"))
				throw new Exception();
		}
	}

	@Override
	public boolean setBroken(String contractAddress, String uuid) throws Exception {
		String status = findFactoryPartsByAddress(contractAddress).markAsRecalled(uuid).send().getStatus();
		return status.equalsIgnoreCase("0x1");
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

	@Override
	public boolean changeOwner(Map<Part, String> parts) throws Exception {
		String address = "";
		FactoryParts contract = null;
		for (Part part : parts.keySet()) {
			String contractAddress = part.getContractAddress();
			if (!contractAddress.equalsIgnoreCase(address)) {
				address = contractAddress;
				contract = findFactoryPartsByAddress(address);
			}
			TransactionReceipt send = contract.changeOwner(part.getPartId(), parts.get(part)).send();
			return new Boolean(send.getStatus());
		}
		return false;
	}

}
