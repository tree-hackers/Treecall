package com.ethmeff.factorybackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.connector.impl.eth.EthBCConnector;

@Configuration
public class AppConfig {
	@Bean
	public BCConnector bcConnector() {
		return new EthBCConnector();
	}

}
