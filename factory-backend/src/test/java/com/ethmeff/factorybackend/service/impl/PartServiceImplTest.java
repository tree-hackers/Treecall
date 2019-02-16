package com.ethmeff.factorybackend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.service.PartService;

@Service
public class PartServiceImplTest extends TestBase implements PartService {

	@Override
	public void addPart(List<Part> parts) throws Exception {
		if (parts.isEmpty()) {
			throw new Exception();
		}
	}

	@Override
	public Part setBroken(Long id) throws Exception {
		if (id == 0) {
			throw new Exception();
		}
		Part part = getTestPart();
		part.setIsBroken(true);
		return part;
	}

}
