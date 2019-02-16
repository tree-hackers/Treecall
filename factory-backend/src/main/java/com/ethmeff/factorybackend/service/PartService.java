package com.ethmeff.factorybackend.service;

import java.util.List;

import com.ethmeff.factorybackend.model.Part;

public interface PartService {
	public void addPart(List<Part> parts) throws Exception;

	public Part setBroken(Long id) throws Exception;
}
