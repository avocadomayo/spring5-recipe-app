package ca.wendyliu.spring5recipeapp.repository;

import ca.wendyliu.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository uomRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    // The below annotation causes the Spring Context to re-load after this test
    // @DirtiesContext
    public void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Cup");
        assertEquals("Cup", uomOptional.get().getDescription());
    }
}