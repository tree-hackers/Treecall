package com.ethmeff.factorybackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.Collections;
import java.util.UUID;

import org.springframework.test.web.servlet.ResultActions;

import com.ethmeff.factorybackend.model.Part;

public class TestBase {
	protected static final String KLIMAANLAGE = "Klimaanlage";

	protected Part getTestPart() {
		return new Part(KLIMAANLAGE, new BigInteger("1"), UUID.randomUUID().toString(), false, Collections.EMPTY_LIST);
	}

	protected void isTestPart(Part savePart) {
		assertThat(savePart.getId()).isGreaterThan(0L);
		assertThat(savePart.getName()).isEqualTo(KLIMAANLAGE);
		assertThat(savePart.getPartId()).isNotNull();
	}

	protected void checkIsTestPartJson(ResultActions resultActions, boolean isBroken) throws Exception {
		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Klimaanlage")))
				.andExpect(jsonPath("$.batch", is(1))).andExpect(jsonPath("$.contractAddress", is("")))
				.andExpect(jsonPath("$.isBroken", is(isBroken)));
	}

	protected void checkIsTestPartJsonInList(ResultActions resultActions, boolean isBroken) throws Exception {
		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.[0].name", is("Klimaanlage")))
				.andExpect(jsonPath("$.[0].batch", is(1))).andExpect(jsonPath("$.[0].contractAddress", is("")))
				.andExpect(jsonPath("$.[0].isBroken", is(isBroken)));
	}
}
