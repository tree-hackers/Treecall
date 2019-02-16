package com.ethmeff.factorybackend.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
	public boolean setBroken(Long id) throws Exception {
		if (id == 0) {
			throw new Exception();
		}
		Part part = getTestPart();
		part.setIsBroken(true);
		return true;
	}

	@Override
	public List<Part> getAllParts() {
		return Arrays.asList(getTestPart());
	}

	@Override
	public boolean changeOwner(Map<Part, String> parts) throws Exception {
		return true;
	}

}
