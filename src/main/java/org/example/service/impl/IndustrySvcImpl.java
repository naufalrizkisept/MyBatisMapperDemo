package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.Industry;
import org.example.repo.IndustryRepo;
import org.example.service.IndustrySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class IndustrySvcImpl implements IndustrySvc {

    @Autowired
    private IndustryRepo industryRepo;

    @Override
    @Cacheable(value = "listIndustryCache", cacheManager = "cacheManager")
    public List<Industry> findAllIndustries() {
        return industryRepo.findAllIndustries();
    }

    @Override
    @Cacheable(value = "industryCache", cacheManager = "cacheManager")
    public Industry findIndustryById(Long id) {
        return industryRepo.findIndustryById(id);
    }

    @Override
    @CachePut(value = "saveIndustryCache", cacheManager = "cacheManager")
    public void saveIndustry(Industry industry) {
        industryRepo.saveIndustry(industry);
    }

    @Override
    @CachePut(value = "updateIndustryCache", cacheManager = "cacheManager")
    public void updateIndustry(Industry industry) {
        industryRepo.updateIndustry(industry);
    }

    @Override
    @CacheEvict(value = "deleteIndustryCache", beforeInvocation = true, cacheManager = "cacheManager")
    public void deleteIndustry(Long id) {
        industryRepo.deleteIndustry(id);
    }
}
