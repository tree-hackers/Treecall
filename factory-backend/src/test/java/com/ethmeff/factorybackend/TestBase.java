package com.ethmeff.factorybackend;

import com.ethmeff.factorybackend.model.Part;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {
    protected static final String KLIMAANLAGE = "Klimaanlage";

    protected Part getTestPart(){
        return new Part(KLIMAANLAGE, 1, "", UUID.randomUUID().toString());
    }

    protected void isTestPart(Part savePart) {
        assertThat(savePart.getId()).isGreaterThan(0L);
        assertThat(savePart.getName()).isEqualTo(KLIMAANLAGE);
        assertThat(savePart.getPartId()).isNotNull();
    }
}
