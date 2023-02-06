package com.versus.www.service;

import com.versus.www.entity.Company;
import com.versus.www.repo.CompanyRepo;
import java.util.logging.Logger;
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
        return companyRepo.findAll();
    }

    public Optional getCompanyByNip (String nip) {
        Optional companyOptional = companyRepo.findCompanyByNip(nip);
        log.info("Company Optional = " + companyOptional);
        if (!companyOptional.isPresent()) {
            throw new IllegalStateException("Chosen company doesn't exists");
        }
        return companyOptional;
    }
}
