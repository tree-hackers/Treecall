package com.ethmeff.factorybackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.model.PartTree;
import com.ethmeff.factorybackend.repository.PartRepository;
import com.ethmeff.factorybackend.service.PartService;

@Service
public class PartServiceImpl implements PartService {
	@Autowired
	private BCConnector bcConnector;
	@Autowired
	private PartRepository repo;

	public void addPart(List<Part> parts) throws Exception {
		bcConnector.addParts(parts);
		repo.saveAll(parts);
	}

	@Override
	public boolean setBroken(Long id) throws Exception {
		Part part = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return bcConnector.setBroken(part.getContractAddress(), part.getPartId());
	}

	@Override
	public List<PartTree> getAllParts() throws Exception {
		List<PartTree> partTrees = new ArrayList<>();
		List<Part> findAll = repo.findAll();
		for (Part part : findAll) {
			partTrees.add(bcConnector.getAllOwnParts(part));
		}
		return partTrees;
	}

	@Override
	public boolean changeOwner(Map<Part, String> parts) throws Exception {
		return bcConnector.changeOwner(parts);
	}

	@Override
	public Part getPartInfo(Long id) throws Exception {
		Part part = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		PartTree allOwnParts = bcConnector.getAllOwnParts(part);
		return allOwnParts.getPart();
	}
}
