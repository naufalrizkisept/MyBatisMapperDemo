package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.Company;
import org.example.repo.CompanyRepo;
import org.example.service.CompanySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanySvcImpl implements CompanySvc {

    @Autowired
    private CompanyRepo companyRepo;
    @Override
    public List<Company> findAllCompanies() {
        return companyRepo.findAllCompanies();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepo.findCompanyById(id);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepo.saveCompany(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepo.updateCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepo.deleteCompany(id);
    }
}
