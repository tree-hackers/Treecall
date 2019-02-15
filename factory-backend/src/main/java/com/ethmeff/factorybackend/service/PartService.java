package com.ethmeff.factorybackend.service;

import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import java.util.UUID;

@Service
public class PartService {
    @Autowired
    private Web3j web3j;
    @Autowired
    private PartRepository repo;

    public Part createPart(Part part) {
        part.setPartId(UUID.randomUUID().toString());
        if(repo.count()==0){
            //TODO deploy contract
        }

        return null;
    }
}
