package com.squarejobs.www.controller;

import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.service.CompanyAccountService;
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

@RestController
public class CompanyAccountController {
    private static final Logger log =  LoggerFactory.getLogger(CompanyAccountController.class);

    private final CompanyAccountService companyAccountService;

    @Autowired
    public CompanyAccountController (CompanyAccountService companyAccountService) {
        this.companyAccountService = companyAccountService;
    }

    @PostMapping(value="/saveCompanyAccount", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> saveNewCompanyAccountInDB (@ModelAttribute  CompanyAccount companyAccount,
                                                                 @RequestParam("file") MultipartFile file) {
        try {
            CompanyAccount newCompanyAccount = new CompanyAccount();
            newCompanyAccount.setEmail(companyAccount.getEmail());
            newCompanyAccount.setPassword(companyAccount.getPassword());
            newCompanyAccount.setEmailStatus(companyAccount.getEmailStatus());
            newCompanyAccount.setPhoto(CommonUtils.convertMultipartFileToBytes(file));
            companyAccountService.saveCompanyAccount(newCompanyAccount);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (CompanyIsAlreadyExistException | IOException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.ok(HttpStatus.CONFLICT);
        }
    }
}
