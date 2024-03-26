package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.Industry;
import org.example.repo.IndustryRepo;
import org.example.service.IndustrySvc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class IndustrySvcImpl implements IndustrySvc {

    private IndustryRepo industryRepo;

    @Override
    public List<Industry> findAllIndustries() {
        return industryRepo.findAllIndustries();
    }

    @Override
    public Industry findIndustryById(Long id) {
        return industryRepo.findIndustryById(id);
    }

    @Override
    public void saveIndustry(Industry industry) {
        industryRepo.saveIndustry(industry);
    }

    @Override
    public void updateIndustry(Industry industry) {
        industryRepo.updateIndustry(industry);
    }

    @Override
    public void deleteIndustry(Long id) {
        industryRepo.deleteIndustry(id);
    }
}
