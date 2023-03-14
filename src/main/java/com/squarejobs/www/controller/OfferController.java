package com.squarejobs.www.controller;

import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.Offer;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.service.CompanyService;
import com.squarejobs.www.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    private static final Logger log =  LoggerFactory.getLogger(OfferController.class);

    private final OfferService offerService;
    private final CompanyService companyService;

    @Autowired
    public OfferController(OfferService offerService, CompanyService companyService) {
        this.offerService = offerService;
        this.companyService = companyService;
    }

    @PostMapping(value="/saveOffer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveOffer (@RequestBody Offer inputOffer) {
        try {
            // проверка из Сессии, что это тот CompanyAccount который нам нужен
            Company company = new Company();
            /**ПОКА ЧТО УСТАНОВЛЮ В РУЧНУЮ CompanyName, Nip, НО ПОСЛЕ SESSION ДОЛЖЕН ВПИХИВАТЬ ЭТИ ПОЛЯ*/
            company.setCompanyName("Google");
            company.setNip("000-000-000");
            company = companyService.saveCompany(company);

            inputOffer.setCompany(company);

            offerService.saveOffer(inputOffer);
        } catch (CompanyIsAlreadyExistException ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
