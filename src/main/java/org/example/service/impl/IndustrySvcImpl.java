package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.Industry;
import org.example.repo.IndustryRepo;
import org.example.service.IndustrySvc;
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
public class IndustrySvcImpl implements IndustrySvc {

    @Autowired
    private IndustryRepo industryRepo;

    @Override
    @Cacheable(value = "listIndustryCache")
    public List<Industry> findAllIndustries() {
        return industryRepo.findAllIndustries();
    }

    @Override
    @Cacheable(value = "industryCache", key = "'industry_' + #id")
    public Industry findIndustryById(@PathVariable("id") Long id) {
        return industryRepo.findIndustryById(id);
    }

    @Override
    @CachePut(value = "saveIndustryCache", key = "#industry.industryId")
    public void saveIndustry(@RequestBody Industry industry) {
        industryRepo.saveIndustry(industry);
    }

    @Override
    @Caching(evict = @CacheEvict(value = "industryCache", key = "'industry_' + #industry.industryId", beforeInvocation = true), put = @CachePut(value = "updateIndustryCache", key = "'industry_' + #industry.industryId"))
    public void updateIndustry(@RequestBody Industry industry) {
        industryRepo.updateIndustry(industry);
    }

    @Override
    @CacheEvict(value = "deleteIndustryCache", key = "'industry_' + #id")
    public void deleteIndustry(@PathVariable("id") Long id) {
        industryRepo.deleteIndustry(id);
    }
}
