package com.ethmeff.factorybackend;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.UUID;

import com.ethmeff.factorybackend.model.Part;

public class TestBase {
	protected static final String KLIMAANLAGE = "Klimaanlage";

	protected Part getTestPart() {
		return new Part(KLIMAANLAGE, new BigInteger("1"), "", UUID.randomUUID().toString(), false);
	}

	protected void isTestPart(Part savePart) {
		assertThat(savePart.getId()).isGreaterThan(0L);
		assertThat(savePart.getName()).isEqualTo(KLIMAANLAGE);
		assertThat(savePart.getPartId()).isNotNull();
	}
}
