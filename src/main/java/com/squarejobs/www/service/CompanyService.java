package com.squarejobs.www.service;

import com.squarejobs.www.entity.Company;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.exceptions.NipIsAlreadyInDBException;
import com.squarejobs.www.exceptions.RequiredCompanyFieldsCouldntBeNullException;
import com.squarejobs.www.repo.CompanyRepo;

import java.io.IOException;
import java.util.logging.Logger;

import com.squarejobs.www.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private static final Logger log = Logger.getLogger(CompanyService.class.getName());
    private final CompanyRepo companyRepo;


    @Autowired
    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> getAllCompanies() {
        return (List<Company>) companyRepo.findAll();
    }

    public Company getCompanyByNip (String nip) throws IOException {
        Optional companyOptional = Optional.of(companyRepo.findCompanyByNip(nip));
        if (companyOptional.isPresent()) {
            log.info("COMPNY IS PRESENT ALREADY");
            throw new IllegalStateException("Chosen company doesn't exists");
        } else {
            return companyRepo.findCompanyByNip(nip);
        }
    }

    public Company getCompanyByNipLaterDelete (String nip) throws IOException {
       return companyRepo.findCompanyByNipLaterDelete(nip);
    }

    public Company saveCompany (Company company) throws RequiredCompanyFieldsCouldntBeNullException, NipIsAlreadyInDBException {
        Company savedCompany = null;
        // добавь обработку исключения когда nip не unique
        if (companyRepo.findAll().isEmpty() || !companyRepo.findAll().contains(company) ) {
            company.setIntroductionDate(CommonUtils.getCurrentTime());
            CommonUtils.checkIfRequiredCompanyFieldsAreEmpty(company);
            savedCompany = companyRepo.save(company);
        } else {
            throw new CompanyIsAlreadyExistException("Company is already exist in DB");
        }
        return savedCompany;
    }
}
