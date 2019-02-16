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
import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.connector.impl.mock.MockBlockchainConnector;
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

		@Bean
		public BCConnector bcConnector() {
			return new MockBlockchainConnector();
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

	@Test
	public void testSetBroken() throws Exception {
		Part testPart = getTestPart();
		Part part = repo.save(testPart);
		Part setBroken = service.setBroken(part.getId());
		assertThat(setBroken.getIsBroken()).isTrue();
		assertThat(setBroken.getName()).isEqualTo(testPart.getName());
	}

}
