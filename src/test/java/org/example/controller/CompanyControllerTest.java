package org.example.controller;

import org.example.model.Company;
import org.example.service.CompanySvc;
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
public class CompanyControllerTest {

    @Mock
    private CompanySvc companySvc;

    @InjectMocks
    private CompanyController companyController;

    @Test
    public void testFindAllCompanies() {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company(1L, "Company A", "Test A", 1L));
        companyList.add(new Company(2L, "Company B", "Test B", 2L));
        when(companySvc.findAllCompanies()).thenReturn(companyList);

        ResponseEntity<List<Company>> response = companyController.findAllCompanies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(companyList, response.getBody());
    }

    @Test
    public void testFindCompanyById() {
        Long companyId = 1L;
        Company company = new Company(1L, "Company A", "Test A", 1L);
        when(companySvc.findCompanyById(companyId)).thenReturn(company);

        ResponseEntity<Company> response = companyController.findCompanyById(companyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(company, response.getBody());
    }

    @Test
    public void testFindCompanyByIdNotFound() {
        Long companyId = 1L;
        when(companySvc.findCompanyById(companyId)).thenReturn(null);

        ResponseEntity<Company> response = companyController.findCompanyById(companyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveCompany() {
        Company company = new Company(1L, "Company A", "Test A", 1L);
        doNothing().when(companySvc).saveCompany(company);

        ResponseEntity<Void> response = companyController.saveCompany(company);

        verify(companySvc, times(1)).saveCompany(company);
        assertSame(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateCompany() {
        Long companyId = 1L;
        Company company = new Company();
        doNothing().when(companySvc).updateCompany(company);

        ResponseEntity<Void> response = companyController.updateCompany(companyId, company);

        verify(companySvc, times(1)).updateCompany(company);
        assertSame(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteCompany() {
        Long companyId = 1L;
        doNothing().when(companySvc).deleteCompany(companyId);

        ResponseEntity<Void> response = companyController.deleteCompany(companyId);

        verify(companySvc, times(1)).deleteCompany(companyId);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
