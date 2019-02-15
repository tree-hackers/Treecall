package com.ethmeff.factorybackend.repository;

import com.ethmeff.factorybackend.TestBase;
import com.ethmeff.factorybackend.model.Part;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class PartRepositoryTest extends TestBase {

    @Autowired
    private PartRepository repo;

    @Test
    public void testSavePart(){
        Part savePart = repo.save(getTestPart());
        isTestPart(savePart);
    }

    @Test
    public void testIsFirstPart(){
        assertThat(repo.count()).isEqualTo(0L);
        repo.save(getTestPart());
        assertThat(repo.count()).isEqualTo(1L);
    }

}
