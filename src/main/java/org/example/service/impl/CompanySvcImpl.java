package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.Company;
import org.example.repo.CompanyRepo;
import org.example.service.CompanySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class CompanySvcImpl implements CompanySvc {

    @Autowired
    private CompanyRepo companyRepo;
    @Override
    @Cacheable(value = "listCompanyCache")
    public List<Company> findAllCompanies() {
        return companyRepo.findAllCompanies();
    }

    @Override
    @Cacheable(value = "companyCache", key = "'company_' + #id")
    public Company findCompanyById(@PathVariable("id") Long id) {
        return companyRepo.findCompanyById(id);
    }

    @Override
    @CachePut(value = "saveCompanyCache", key = "#company.companyId")
    public void saveCompany(@RequestBody Company company) {
        companyRepo.saveCompany(company);
    }

    @Override
    @Caching(evict = @CacheEvict(value = "companyCache", key = "'company_' + #company.companyId", beforeInvocation = true), put = @CachePut(value = "updateCompanyCache", key = "'company_' + #company.companyId"))
    public void updateCompany(@RequestBody Company company) {
        companyRepo.updateCompany(company);
    }

    @Override
    @CacheEvict(value = "deleteCompanyCache", key = "'company_' + #id", beforeInvocation = true)
    public void deleteCompany(@PathVariable("id") Long id) {
        companyRepo.deleteCompany(id);
    }
}
