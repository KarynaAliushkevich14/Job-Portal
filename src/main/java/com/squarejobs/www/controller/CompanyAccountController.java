package com.squarejobs.www.controller;

import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.exceptions.NipIsAlreadyInDBException;
import com.squarejobs.www.exceptions.RequiredCompanyFieldsCouldntBeNullException;
import com.squarejobs.www.service.CompanyAccountService;
import com.squarejobs.www.service.CompanyService;
import com.squarejobs.www.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CompanyAccountController {
    private static final Logger log =  LoggerFactory.getLogger(CompanyAccountController.class);

    private final CompanyAccountService companyAccountService;
    private final CompanyService companyService;

    @Autowired
    public CompanyAccountController (CompanyAccountService companyAccountService, CompanyService companyService) {
        this.companyAccountService = companyAccountService;
        this.companyService = companyService;
    }

    @GetMapping(value="/getAllCompanyAccounts")
    public ResponseEntity<List<CompanyAccount>> getAllEmployees() {
        List<CompanyAccount> listOfCompanyAccounts = companyAccountService.findAllCompanyAccounts();
        return ResponseEntity.ok(listOfCompanyAccounts);
    }

    @PostMapping(value="/saveCompanyAccount", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> saveNewCompanyAccountInDB (@ModelAttribute CompanyAccount companyAccount,
                                                                 @RequestParam("file") MultipartFile file) {
        try {
            CompanyAccount newCompanyAccount = new CompanyAccount();
            newCompanyAccount.setEmail(companyAccount.getEmail());
            newCompanyAccount.setPassword(companyAccount.getPassword());
            newCompanyAccount.setEmailStatus(companyAccount.getEmailStatus());
            newCompanyAccount.setPhoto(CommonUtils.convertMultipartFileToBytes(file));

            // добавить исключение чтобы поля company были заполнены, иначе в БД для companyAccount не привязывает company
            if (companyAccount.getCompany() != null) {
                Company company = new Company();
                company.setCompanyName(companyAccount.getCompany().getCompanyName());
                company.setNip(companyAccount.getCompany().getNip());
                company.setState(companyAccount.getCompany().getState());
                company.setCompanySize(companyAccount.getCompany().getCompanySize());
                company.setNrBuilding(companyAccount.getCompany().getNrBuilding());
                company.setNrStreet(companyAccount.getCompany().getNrStreet());
                company.setCountry(companyAccount.getCompany().getCountry());
                company.setProvince(companyAccount.getCompany().getProvince());
                company.setCity(companyAccount.getCompany().getCity());
                company.setStreet(companyAccount.getCompany().getStreet());
                //save in DB
                company = companyService.saveCompany(company);
                // set company to companyAccount
                newCompanyAccount.setCompany(company);
            }
            companyAccountService.saveCompanyAccount(newCompanyAccount);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (CompanyIsAlreadyExistException | IOException | RequiredCompanyFieldsCouldntBeNullException |
                 NipIsAlreadyInDBException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.ok(HttpStatus.CONFLICT);
        }
    }
}
