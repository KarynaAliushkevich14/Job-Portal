package com.squarejobs.www.controller;

import com.squarejobs.www.DTO.CompanyDTO;
import com.squarejobs.www.entity.Company;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.exceptions.NipIsAlreadyInDBException;
import com.squarejobs.www.exceptions.RequiredCompanyFieldsCouldntBeNullException;
import com.squarejobs.www.mapper.MapStructMapper;
import com.squarejobs.www.service.CompanyAccountService;
import com.squarejobs.www.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    private static final Logger log =  LoggerFactory.getLogger(CompanyController.class);

    private final CompanyService companyService;
    private final CompanyAccountService companyAccountService;


    @Autowired
    public CompanyController(CompanyService companyService, CompanyAccountService companyAccountService) {
        this.companyService = companyService;
        this.companyAccountService = companyAccountService;
    }

    @GetMapping(value = "/getAllCompanies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies () {
        List<CompanyDTO> allDTOsCompanies = MapStructMapper.INSTANCE.toCompanyDTOs(companyService.getAllCompanies());
        return ResponseEntity.ok(allDTOsCompanies);
    }


    @PostMapping(value="/saveCompany",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveNewCompanyInDB (@RequestBody Company inputCompany) {
        try {
            Company newCompany = companyService.saveCompany(inputCompany);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (CompanyIsAlreadyExistException | RequiredCompanyFieldsCouldntBeNullException |
                 NipIsAlreadyInDBException ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.CONFLICT);
    }
}
