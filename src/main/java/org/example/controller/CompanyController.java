package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Company;
import org.example.service.CompanySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company", name = "Company Controller")
@Tag(name = "Company", description = "Company endpoints")
public class CompanyController {

    @Autowired
    private CompanySvc companySvc;

    @Operation(summary = "Get all companies", description = "Get all companies from database")
    @GetMapping(value = "/all", name = "Get all companies")
    public ResponseEntity<List<Company>> findAllCompanies() {
        return new ResponseEntity<>(companySvc.findAllCompanies(), HttpStatus.OK);
    }

    @Operation(summary = "Get specific company", description = "Get specific company by using ID from database")
    @GetMapping(value = "/{id}", name = "Get company by ID")
    public ResponseEntity<Company> findCompanyById(@PathVariable("id") Long id) {
        return (companySvc.findCompanyById(id) != null) ? new ResponseEntity<>(companySvc.findCompanyById(id), HttpStatus.OK) : new ResponseEntity<>(companySvc.findCompanyById(id), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Save company", description = "Save company to database")
    @PostMapping(value = "/save", name = "Save company")
    public ResponseEntity<Void> saveCompany(@RequestBody Company company) {
        companySvc.saveCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update company", description = "Update company using ID to database")
    @PutMapping(value = "/update/{id}", name = "Update company using ID")
    public ResponseEntity<Void> updateCompany(@PathVariable("id") Long id, @RequestBody Company company) {
        company.setCompanyId(id);
        companySvc.updateCompany(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete company", description = "Delete company using ID from database")
    @DeleteMapping(value = "/{id}", name = "Delete company using ID")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companySvc.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
