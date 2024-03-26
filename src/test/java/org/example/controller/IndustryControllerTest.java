package org.example.controller;

import org.example.model.Industry;
import org.example.service.IndustrySvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndustryControllerTest {

    @Mock
    private IndustrySvc industrySvc;

    @InjectMocks
    private IndustryController industryController;

    @Test
    void testFindAllIndustries() {
        List<Industry> industryList = new ArrayList<>();
        industryList.add(new Industry(1L, "Industry A", "Test A"));
        industryList.add(new Industry(2L, "Industry B", "Test B"));
        when(industrySvc.findAllIndustries()).thenReturn(industryList);

        ResponseEntity<List<Industry>> response = industryController.findAllIndustries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(industryList, response.getBody());
    }

    @Test
    void testFindIndustryById() {
        Long industryId = 1L;
        Industry industry = new Industry(1L, "Industry A", "Test A");
        when(industrySvc.findIndustryById(industryId)).thenReturn(industry);

        ResponseEntity<Industry> response = industryController.findIndustryById(industryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(industry, response.getBody());
    }

    @Test
    void testFindIndustryByIdNotFound() {
        Long industryId = 1L;
        when(industrySvc.findIndustryById(industryId)).thenReturn(null);

        ResponseEntity<Industry> response = industryController.findIndustryById(industryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testSaveIndustry() {
        Industry industry = new Industry();
        doNothing().when(industrySvc).saveIndustry(industry);

        ResponseEntity<Void> response = industryController.saveIndustry(industry);

        verify(industrySvc, times(1)).saveIndustry(industry);
        assertSame(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateIndustry() {
        Long industryId = 1L;
        Industry industry = new Industry();
        doNothing().when(industrySvc).updateIndustry(industry);

        ResponseEntity<Void> response = industryController.updateIndustry(industryId, industry);

        verify(industrySvc, times(1)).updateIndustry(industry);
        assertSame(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteIndustry() {
        Long industryId = 1L;
        doNothing().when(industrySvc).deleteIndustry(industryId);

        ResponseEntity<Void> response = industryController.deleteIndustry(industryId);

        verify(industrySvc, times(1)).deleteIndustry(industryId);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
 }
