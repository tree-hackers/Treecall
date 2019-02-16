package com.ethmeff.factorybackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ethmeff.factorybackend.service.PartService;
import com.ethmeff.factorybackend.service.impl.PartServiceImplTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryBackendApplicationTests {

	@TestConfiguration
	static class FactoryBackendApplicationTestsConfig {
		@Bean
		public PartService service() {
			return new PartServiceImplTest();
		}
	}

	@Test
	public void contextLoads() {
	}

}
