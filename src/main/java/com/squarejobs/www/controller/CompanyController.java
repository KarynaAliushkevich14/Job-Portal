package com.squarejobs.www.controller;

import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
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

import java.io.IOException;
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
    public ResponseEntity getAllCompanies (){
        List<Company> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

    @PostMapping(value = "/getCompany")
    public ResponseEntity getCompanyByNip (@RequestBody String companyNip){
        log.info("COMPANY_CONTROLLER - getCompany() - nip = " + companyNip);
        ResponseEntity entity = null;
        try {
           entity = ResponseEntity.ok(companyService.getCompanyByNip(companyNip));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @PostMapping(value="/saveCompany",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveNewCompanyInDB (@RequestBody Company inputCompany) {
        try {
            if (inputCompany.getCompanyAccount() != null) { // если пользователь ввел данные новосозданного companyAccount
                CompanyAccount companyAccount = new CompanyAccount(); // создаём companyAccount
                companyAccount.setEmail(inputCompany.getCompanyAccount().getEmail());
                companyAccount.setPassword(inputCompany.getCompanyAccount().getPassword());
                companyAccount.setEmailStatus(inputCompany.getCompanyAccount().getEmailStatus());

                companyAccount = companyAccountService.saveCompanyAccount(companyAccount); // ! save companyAccount

                inputCompany.setCompanyAccount(companyAccount); // целая company.setCompanyAccount(companyAccount) - привязываем к компании аккаунтКомпании

                //companyAccount.setCompany(newCompany); //  companyAccount привязываем к новому объекту Company newCompany


            }
            // Новая Компания. После того как создали CompanyAccount сначала. У нее есть fk_account.
            Company newCompany = companyService.saveCompany(inputCompany);

        } catch (CompanyIsAlreadyExistException ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
