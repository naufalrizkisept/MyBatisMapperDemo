package org.example.repo;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.Industry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IndustryRepo {
    List<Industry> findAllIndustries();
    Industry findIndustryById(Long id);
    void saveIndustry(Industry industry);
    void updateIndustry(Industry industry);
    void deleteIndustry(Long id);
}
