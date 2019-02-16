package com.ethmeff.factorybackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.connector.impl.mock.MockBlockchainConnector;
import com.ethmeff.factorybackend.model.Part;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class PartRepositoryTest extends TestBase {

	@TestConfiguration
	static class PartRepositoryTestConfig {
		@Bean
		public BCConnector bcConnector() {
			return new MockBlockchainConnector();
		}
	}

	@Autowired
	private PartRepository repo;

	@Test
	public void testSavePart() {
		Part savePart = repo.save(getTestPart());
		isTestPart(savePart);
	}

	@Test
	public void testIsFirstPart() {
		assertThat(repo.count()).isEqualTo(0L);
		repo.save(getTestPart());
		assertThat(repo.count()).isEqualTo(1L);
	}

}
