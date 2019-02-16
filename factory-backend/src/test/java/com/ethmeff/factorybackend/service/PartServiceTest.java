package com.ethmeff.factorybackend.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

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
@Transactional
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
		boolean setBroken = service.setBroken(part.getId());
		assertThat(setBroken).isTrue();
	}

	@Test
	public void testGetAllParts() {
		assertThat(service.getAllParts().size()).isEqualTo(0);
		repo.save(getTestPart());
		assertThat(service.getAllParts().size()).isEqualTo(1);
	}

	@Test
	public void testChangeOwner() throws Exception {
		HashMap<Part, String> hashMap = new HashMap<>();
		hashMap.put(getTestPart(), "0x66d55007de7e8cb7a1aad39c7d2cc798329f9374");
		assertThat(service.changeOwner(hashMap)).isTrue();
	}
}
