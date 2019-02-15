package com.ethmeff.factorybackend.blockchain;

import com.ethmeff.factorybackend.model.Part;

public interface BCConnector {
    public Part deploy(Part part);
    public Part setBroken(String uuid);
    public Part addPart(String uuidParentPart, Part... parts);
}
