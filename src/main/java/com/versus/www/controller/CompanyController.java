package com.versus.www.controller;

import com.versus.www.entity.Company;
import com.versus.www.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CompanyController {
    private static final Logger log = Logger.getLogger(CompanyController.class.getName());

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/getAllCompanies")
    public ResponseEntity getAllCompanies (){
        List<Company> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

    @PostMapping(value = "/getCompany")
    public ResponseEntity getCompanyByNip (@RequestBody String nip){
        log.info("COMPANY_CONTROLLER - getCompany() - nip = " + nip);
        return ResponseEntity.ok(companyService.getCompanyByNip(nip));
    }

}
