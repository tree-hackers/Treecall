package com.ethmeff.factorybackend.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.repository.PartRepository;
import com.ethmeff.factorybackend.service.impl.PartServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PartServiceTest extends TestBase {

	@TestConfiguration
	static class PartServiceTestConfig {
		@Bean
		public PartService service() {
			return new PartServiceImpl();
		}
	}

	@Autowired
	private PartServiceImpl service;

	@Autowired
	private PartRepository repo;

	@Test
	public void testAddParts() throws Exception {
		service.addPart(Arrays.asList(getTestPart()));
		List<Part> findAll = repo.findAll();
		assertThat(findAll.size()).isEqualTo(1);
		Part savePart = findAll.get(0);
		isTestPart(savePart);
		assertThat(savePart.getContractAddress()).isNotNull();
	}
}
