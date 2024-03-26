package org.example.repo;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CompanyRepo {
    List<Company> findAllCompanies();
    Company findCompanyById(Long id);
    void saveCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Long id);
}
