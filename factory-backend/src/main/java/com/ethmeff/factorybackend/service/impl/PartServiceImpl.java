package com.ethmeff.factorybackend.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
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

	@Override
	public boolean setBroken(Long id) throws Exception {
		Part part = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return bcConnector.setBroken(part.getContractAddress(), part.getPartId());
	}

	@Override
	public List<Part> getAllParts() throws Exception {
		List<Part> findAll = repo.findAll();
		return bcConnector.getAllTokens(findAll);
	}

	@Override
	public boolean changeOwner(Map<Part, String> parts) throws Exception {
		return bcConnector.changeOwner(parts);
	}

	@Override
	public Part getPartInfo(Long id) throws Exception {
		Part part = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		List<Part> allTokens = bcConnector.getAllTokens(Arrays.asList(part));
		Assert.notNull(allTokens, "should not be null");
		return allTokens.get(0);
	}
}
