package com.squarejobs.www.service;

import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.repo.CompanyAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CompanyAccountService {
    private static final Logger log = Logger.getLogger(CompanyAccountService.class.getName());
    private final CompanyAccountRepo companyAccountRepo;

    @Autowired
    public CompanyAccountService(CompanyAccountRepo companyAccountRepo) {
        this.companyAccountRepo = companyAccountRepo;
    }

    public List<CompanyAccount> findAllCompanyAccounts () {
        return companyAccountRepo.findAll();
    }

    public CompanyAccount saveCompanyAccount (CompanyAccount companyAccount) {
        CompanyAccount savedCompanyAcoount= null;
        if (companyAccountRepo.findAll().isEmpty() || !companyAccountRepo.findAll().contains(companyAccount)) {
            savedCompanyAcoount = companyAccountRepo.save(companyAccount);
        } else {
            throw new CompanyIsAlreadyExistException("Company account is already exist in DB");
        }
        return savedCompanyAcoount;
    }

    public CompanyAccount findCompanyByEmail(String email) {
       return companyAccountRepo.findCompanyAccountByEmail(email);
    }
}
