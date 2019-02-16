package com.ethmeff.factorybackend;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.ethmeff.factorybackend.blockchain.connector.BCConnector;
import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.repository.PartRepository;

@SpringBootApplication
public class FactoryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryBackendApplication.class, args);
	}

	@Autowired
	private PartRepository repo;
	@Autowired
	private BCConnector bcConnector;
	@Value("${privatekey}")
	private String privatekey;

	@Bean
	@Profile("daimler")
	public CommandLineRunner demoDataDAG() {
		return args -> {
			String address = bcConnector.deploy();
			List<Part> asList = Arrays.asList(new Part("Headlights", new BigInteger("1"), address, "", false),
					new Part("Breaks", new BigInteger("4"), address, "", false),
					new Part("Engine", new BigInteger("101"), address, "", false),
					new Part("Airbags", new BigInteger("20234"), address, "", false),
					new Part("Headlight", new BigInteger("733"), address, "", false),
					new Part("Breaks", new BigInteger("785"), address, "", false),
					new Part("Engine", new BigInteger("3457"), address, "", false),
					new Part("Airbags", new BigInteger("68"), address, "", false),
					new Part("Headlight", new BigInteger("861"), address, "", false),
					new Part("Breaks", new BigInteger("995"), address, "", false),
					new Part("Engine", new BigInteger("567"), address, "", false),
					new Part("Airbags", new BigInteger("987"), address, "", false),
					new Part("Headlight", new BigInteger("94"), address, "", false));
			bcConnector.addParts(asList);
			repo.saveAll(asList);

			Map<Part, String> changeOwnerMap = new HashMap<Part, String>();
			asList.forEach(it -> changeOwnerMap.put(it, "0x830a8cd285d14925e531ee143574c72c00db6411"));

			bcConnector.changeOwner(changeOwnerMap);
		};
	}

	@Bean
	@Profile("zf")
	public CommandLineRunner demoDataZF() {
		return args -> {
			List<Part> asList = Arrays.asList(new Part("Headlights", new BigInteger("1"), "", "", false),
					new Part("Break Disc", new BigInteger("7"), "", "", false),
					new Part("Break Pads", new BigInteger("23"), "", "", false),
					new Part("Break Pipes", new BigInteger("3"), "", "", false));
			bcConnector.addParts(asList);
			repo.saveAll(asList);

			Map<Part, String> changeOwnerMap = new HashMap<Part, String>();
			asList.forEach(it -> changeOwnerMap.put(it, "0x830a8cd285d14925e531ee143574c72c00db6411"));

			bcConnector.changeOwner(changeOwnerMap);
		};
	}
}
