package com.squarejobs.www.service;

import com.squarejobs.www.entity.Offer;
import com.squarejobs.www.exceptions.OfferIsAlreadyExistException;
import com.squarejobs.www.repo.OfferRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    private static final Logger log =  LoggerFactory.getLogger(OfferService.class);
    private final OfferRepo offerRepo;


    @Autowired
    public OfferService (OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    public Offer findOfferByOfferCity (String city) {
        Offer offer = offerRepo.findOfferByOfferCity (city);
        return offer;
    }

    public List<Offer> findAllOffers() {
        List<Offer> offers = offerRepo.findAll();
        return offers;
    }

    public Offer saveOffer (Offer offer) {
        Offer savedOffer = null;
        if (offerRepo.findAll().isEmpty() || !offerRepo.findAll().contains(offer) /* Второе условие не защищает от повторной вставки*/) {
            savedOffer = offerRepo.save(offer);
        } else {
            throw new OfferIsAlreadyExistException("Offer is already exist in DB");
        }
        return savedOffer;
    }
}
