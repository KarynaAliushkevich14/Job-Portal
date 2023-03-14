package com.squarejobs.www.controller;

import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.entity.Offer;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.service.CompanyAccountService;
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
    private final CompanyAccountService companyAccountService;

    @Autowired
    public OfferController(OfferService offerService, CompanyAccountService companyAccountService) {
        this.offerService = offerService;
        this.companyAccountService = companyAccountService;
    }

    @PostMapping(value="/saveOffer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveOffer (@RequestBody Offer inputOffer) {
        try {
            // проверка из Сессии, что это тот CompanyAccount который нам нужен
            CompanyAccount companyAccount = new CompanyAccount();
            /**ПОКА ЧТО УСТАНОВЛЮ В РУЧНУЮ EMAIL NIP, НО ПОСЛЕ SESSION ДОЛЖЕН ВПИХИВАТЬ ЭТИ ПОЛЯ*/
            companyAccount.setEmail("newCompanyAccount@gmail.com");
            companyAccount.setPassword("password123");
            companyAccount = companyAccountService.saveCompanyAccount(companyAccount);


            inputOffer.setCompanyAccount(companyAccount);

            Offer newOffer = offerService.saveOffer(inputOffer);
        } catch (CompanyIsAlreadyExistException ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
