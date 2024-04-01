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
    @Cacheable(value = "listCompanyCache", cacheManager = "cacheManager")
    public List<Company> findAllCompanies() {
        return companyRepo.findAllCompanies();
    }

    @Override
    @Cacheable(value = "companyCache", cacheManager = "cacheManager")
    public Company findCompanyById(Long id) {
        return companyRepo.findCompanyById(id);
    }

    @Override
    @CachePut(value = "saveCompanyCache", cacheManager = "cacheManager")
    public void saveCompany(Company company) {
        companyRepo.saveCompany(company);
    }

    @Override
    @CachePut(value = "updateCompanyCache", cacheManager = "cacheManager")
    public void updateCompany(Company company) {
        companyRepo.updateCompany(company);
    }

    @Override
    @CacheEvict(value = "deleteCompanyCache", beforeInvocation = true, cacheManager = "cacheManager")
    public void deleteCompany(Long id) {
        companyRepo.deleteCompany(id);
    }
}
