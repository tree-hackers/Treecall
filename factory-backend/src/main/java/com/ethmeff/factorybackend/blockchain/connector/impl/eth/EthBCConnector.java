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
import org.web3j.tuples.generated.Tuple6;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.contract.FactoryParts;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.model.PartTree;

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
			if (isNewContractAddress(contractAddress, savedContractAddress)) {
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

	private boolean isNewContractAddress(String contractAddress, String savedContractAddress) {
		return !contractAddress.equalsIgnoreCase(savedContractAddress) || savedContractAddress == null
				|| savedContractAddress.isEmpty();
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

	@Override
	public PartTree getAllOwnParts(Part part) throws Exception {
		PartTree result = new PartTree();

		Part foundPart = new Part(part.getId(), part.getPartId(),
				findWithContractAddressAndPartID(part.getContractAddress(), part.getPartId()));
		List<String> subPartsContracts = foundPart.getSubPartsContracts();
		List<String> subPartsUUID = foundPart.getSubPartsUUID();

		for (int i = 0; i < subPartsContracts.size(); i++) {
			PartTree recurs = getAllOwnParts(new Part(subPartsContracts.get(i), subPartsUUID.get(i)));
			result.addToPartTrees(recurs);
		}
		result.setPart(foundPart);
		return result;
	}

	private Tuple6<String, BigInteger, String, Boolean, List<String>, List<String>> findWithContractAddressAndPartID(
			String contractAddress, String partID) throws Exception {
		FactoryParts contract = findFactoryPartsByAddress(contractAddress);
		Tuple6<String, BigInteger, String, Boolean, List<String>, List<String>> foundPart = contract.getPart(partID)
				.send();
		return foundPart;
	}

}
