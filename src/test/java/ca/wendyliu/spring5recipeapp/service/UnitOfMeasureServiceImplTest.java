package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.commands.UnitOfMeasureCommand;
import ca.wendyliu.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ca.wendyliu.spring5recipeapp.domain.UnitOfMeasure;
import ca.wendyliu.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUoms() {
        // given
        Set<UnitOfMeasure> uoms = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        uoms.addAll(Arrays.asList(uom1, uom2));

        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        // when
        Set<UnitOfMeasureCommand> result = unitOfMeasureService.listAllUoms();

        // then
        assertEquals(result.size(), uoms.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}