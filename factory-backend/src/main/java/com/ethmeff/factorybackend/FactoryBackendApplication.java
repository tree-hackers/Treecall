package com.ethmeff.factorybackend;

import java.math.BigInteger;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.blockchain.connector.impl.eth.EthBCConnector;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.repository.PartRepository;

@SpringBootApplication
public class FactoryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryBackendApplication.class, args);
	}

	@Bean
	public BCConnector bcConnector() {
		return new EthBCConnector();
	}

	@Autowired
	private PartRepository repo;

	@Bean
	@Profile("daimler")
	public CommandLineRunner demoDataDAG() {
		return args -> {
			repo.saveAll(Arrays.asList(new Part("Headligths", new BigInteger("1"), "", "", false),
					new Part("Breaks", new BigInteger("4"), "", "", false),
					new Part("Engine", new BigInteger("101"), "", "", false),
					new Part("Airbags", new BigInteger("20234"), "", "", false),
					new Part("Headlight", new BigInteger("733"), "", "", false),
					new Part("Breaks", new BigInteger("785"), "", "", false),
					new Part("Engine", new BigInteger("3457"), "", "", false),
					new Part("Airbags", new BigInteger("68"), "", "", false),
					new Part("Headlight", new BigInteger("861"), "", "", false),
					new Part("Breaks", new BigInteger("995"), "", "", false),
					new Part("Engine", new BigInteger("567"), "", "", false),
					new Part("Airbags", new BigInteger("987"), "", "", false),
					new Part("Headlight", new BigInteger("94"), "", "", false)));
		};
	}

	@Bean
	@Profile("zf")
	public CommandLineRunner demoDataZF() {
		return args -> {
			repo.saveAll(Arrays.asList(new Part("Headligths", new BigInteger("1"), "", "", false),
					new Part("Break Disc", new BigInteger("7"), "", "", false),
					new Part("Break Pads", new BigInteger("23"), "", "", false),
					new Part("Break Pipes", new BigInteger("3"), "", "", false)));
		};
	}
}
