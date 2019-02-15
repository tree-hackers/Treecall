package com.ethmeff.factorybackend.service;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.model.Part;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartServiceTest extends TestBase {
    @Autowired
    private PartService service;

    @Test
    private void createPart(){
        Part savePart = service.createPart(getTestPart());
        isTestPart(savePart);
        assertThat(savePart.getContractAddress()).isNotNull();
    }
}
