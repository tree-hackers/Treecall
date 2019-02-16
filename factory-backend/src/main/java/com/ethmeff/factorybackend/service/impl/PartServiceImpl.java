package com.ethmeff.factorybackend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethmeff.factorybackend.blockchain.BCConnector;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.repository.PartRepository;
import com.ethmeff.factorybackend.service.PartService;

@Service
public class PartServiceImpl implements PartService {
	@Autowired
	private BCConnector bcConnector;
	@Autowired
	private PartRepository repo;

	public void addPart(List<Part> parts) throws Exception {
		String contractAddress = null;
		if (repo.count() == 0) {
			contractAddress = bcConnector.deploy();
		}
		for (Part part : parts) {
			if (contractAddress != null) {
				part.setContractAddress(contractAddress);
			}
			part.setPartId(UUID.randomUUID().toString());
		}
		bcConnector.addParts(parts);
		repo.saveAll(parts);
	}
}
