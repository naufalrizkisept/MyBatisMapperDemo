package org.example.service;

import org.example.model.Industry;

import java.util.List;

public interface IndustrySvc {
    List<Industry> findAllIndustries();
    Industry findIndustryById(Long id);
    void saveIndustry(Industry industry);
    void updateIndustry(Industry industry);
    void deleteIndustry(Long id);
}
