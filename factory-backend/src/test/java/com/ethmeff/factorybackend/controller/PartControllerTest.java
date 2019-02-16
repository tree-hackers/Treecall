package com.ethmeff.factorybackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.service.PartService;
import com.ethmeff.factorybackend.service.impl.PartServiceImplTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PartController.class)
@EnableAutoConfiguration
public class PartControllerTest extends TestBase {
	@Autowired
	private MockMvc mvc;

	@TestConfiguration
	static class PartControllerTestConfig {
		@Bean
		public PartService partService() {
			return new PartServiceImplTest();
		}
	}

	@Test
	public void testAddParts() throws Exception {
		List<Part> asList = Arrays.asList(getTestPart());
		String testPartAsJson = new ObjectMapper().writeValueAsString(asList);
		mvc.perform(post("/").content(testPartAsJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());
	}

	@Test(expected = Exception.class)
	public void testAddPartsException() throws Exception {
		mvc.perform(post("/").content("[]").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());
	}

}
