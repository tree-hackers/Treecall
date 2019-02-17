package com.ethmeff.factorybackend.service;

import java.util.List;
import java.util.Map;

import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.model.PartTree;

public interface PartService {
	public void addPart(List<Part> parts) throws Exception;

	public boolean setBroken(Long id) throws Exception;

	public List<PartTree> getAllParts() throws Exception;

	public boolean changeOwner(Map<Part, String> parts) throws Exception;

	public Part getPartInfo(Long id) throws Exception;
}
