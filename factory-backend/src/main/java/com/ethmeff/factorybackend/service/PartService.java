package com.ethmeff.factorybackend.service;

import java.util.List;
import java.util.Map;

import com.ethmeff.factorybackend.model.Part;

public interface PartService {
	public void addPart(List<Part> parts) throws Exception;

	public Part setBroken(Long id) throws Exception;

	public List<Part> getAllParts();

	public boolean changeOwner(Map<Part, String> parts) throws Exception;
}
