package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Industry;
import org.example.service.IndustrySvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
@Tag(name = "Industry", description = "Industry endpoints")
public class IndustryController {

    private IndustrySvc industrySvc;

    @Operation(summary = "Get all industries", description = "Get all industries from database")
    @GetMapping("/all")
    public ResponseEntity<List<Industry>> findAllIndustries() {
        return new ResponseEntity<>(industrySvc.findAllIndustries(), HttpStatus.OK);
    }

    @Operation(summary = "Get specific industry", description = "Get specific industry by using ID")
    @GetMapping("/{id}")
    public ResponseEntity<Industry> findIndustryById(@PathVariable("id") Long id) {
        return (industrySvc.findIndustryById(id) != null) ? new ResponseEntity<>(industrySvc.findIndustryById(id), HttpStatus.OK) : new ResponseEntity<>(industrySvc.findIndustryById(id), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Save industry", description = "Save industry to database")
    @PostMapping("/save")
    public ResponseEntity<Void> saveIndustry(@RequestBody Industry industry) {
        industrySvc.saveIndustry(industry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update industry", description = "Update industry using ID to database")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateIndustry(@PathVariable("id") Long id, @RequestBody Industry industry) {
        industry.setIndustryId(id);
        industrySvc.updateIndustry(industry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete industry", description = "Delete industry using ID from database")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable("id") Long id) {
        industrySvc.deleteIndustry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
