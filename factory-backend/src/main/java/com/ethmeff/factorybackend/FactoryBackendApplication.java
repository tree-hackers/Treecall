package com.ethmeff.factorybackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
	@Profile("zf")
	public CommandLineRunner demoDataZF() {
		return args -> {
			String address = bcConnector.deploy();

			List<Part> zfParts = Arrays.asList(DemoData.Break_Disc, DemoData.Break_Pads, DemoData.Break_Pipes);
			zfParts = zfParts.stream().map(it -> it.withContractAddress(address)).collect(Collectors.toList());

			bcConnector.addParts(zfParts);
			repo.saveAll(zfParts);

			Map<Part, String> changeOwnerMap = new HashMap<Part, String>();
			String borrowerAddress = "0x830a8cd285d14925e531ee143574c72c00db6411";
			zfParts.forEach(it -> {
				changeOwnerMap.put(it, borrowerAddress);
			});

			bcConnector.changeOwner(changeOwnerMap);
		};
	}

	@Bean
	@Profile("daimler")
	public CommandLineRunner demoDataDAG() {
		return args -> {
			String daimlerAddress = bcConnector.deploy();

			Part brake = DemoData.brake;
			List<String> zfContractsListed = Arrays.asList(contractAddress, contractAddress, contractAddress);
			brake.setSubPartsContracts(zfContractsListed);

			List<Part> daiParts = Arrays.asList(brake, DemoData.airbag);
			daiParts = daiParts.stream().map(it -> it.withContractAddress(daimlerAddress)).collect(Collectors.toList());
			// daiParts
			List<Part> part = new ArrayList<>();
			part.add(new Part(daimlerAddress, UUID.randomUUID().toString()));
			bcConnector.addParts(daiParts);
			repo.saveAll(daiParts);
			repo.saveAll(Arrays.asList(DemoData.Airbags2, DemoData.Airbags3, DemoData.Brake2, DemoData.Brake3,
					DemoData.Engine2, DemoData.Headlight4));
			String carAddress = "0xa0976540fa883a4db37b8aee693548141259ee11";
			Map<Part, String> changeOwnerMap = new HashMap<Part, String>();
			daiParts.forEach(it -> changeOwnerMap.put(it, carAddress));

			bcConnector.changeOwner(changeOwnerMap);
		};
	}

	@Value("${daimler.contract.address}")
	private String contractAddress;

	@Bean
	@Profile("car")
	public CommandLineRunner demoDataCar() {
		return args -> {

			List<Part> daiParts = Arrays.asList(DemoData.brake, DemoData.airbag, DemoData.headlight, DemoData.engine,
					DemoData.car);
			daiParts = daiParts.stream().map(it -> it.withContractAddress(contractAddress))
					.collect(Collectors.toList());

			repo.saveAll(daiParts);
		};
	}
}
