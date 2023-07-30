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


    public Company getCompanyByNipLaterDelete (String nip) throws IOException {
       return companyRepo.findCompanyByNipLaterDelete(nip);
    }

    public Company saveCompany (Company company) throws RequiredCompanyFieldsCouldntBeNullException, NipIsAlreadyInDBException {
        Company savedCompany = null;
        // добавь обработку исключения когда nip не unique
        if (companyRepo.findAll().isEmpty() || !companyRepo.findAll().contains(company) ) {
            company.setIntroductionDate(CommonUtils.getCurrentTime());
            log.info("KARYNA: state= "+ company.getState() + " companySize= " + company.getCompanySize() + " introductionDate= " + company.getIntroductionDate()
                    + " nrBuilding= " +  company.getNrBuilding() + " nrStreet= " + company.getNrStreet() + " province= " + company.getProvince() + " state= " + company.getState() + " street= " + company.getStreet()
                    + " country= " + company.getCountry() + " city= " + company.getCity() + " nip= " + company.getNip());
            CommonUtils.checkIfRequiredCompanyFieldsAreEmpty(company);
            savedCompany = companyRepo.save(company);
        } else {
            throw new CompanyIsAlreadyExistException("Company is already exist in DB");
        }
        return savedCompany;
    }
}
