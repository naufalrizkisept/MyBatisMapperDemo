package org.example.service;

import org.example.model.Company;
import java.util.List;

public interface CompanySvc {
    List<Company> findAllCompanies();
    Company findCompanyById(Long id);
    void saveCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Long id);
}
