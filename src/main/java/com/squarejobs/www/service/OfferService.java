package com.squarejobs.www.service;

import com.squarejobs.www.controller.OfferController;
import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.Offer;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.exceptions.OfferIsAlreadyExistException;
import com.squarejobs.www.repo.OfferRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    private static final Logger log =  LoggerFactory.getLogger(OfferService.class);
    private final OfferRepo offerRepo;

    @Autowired
    public OfferService (OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    public Offer saveOffer (Offer offer) {
        Optional pulledCompany = offerRepo.findOfferByOfferName(offer.getOfferName());
        Offer savedOffer = null;
        if (!pulledCompany.isPresent()) {
            savedOffer = offerRepo.save(offer);
        } else {
            throw new OfferIsAlreadyExistException("Offer is already exist in DB");
        }
        return savedOffer;
    }
}
