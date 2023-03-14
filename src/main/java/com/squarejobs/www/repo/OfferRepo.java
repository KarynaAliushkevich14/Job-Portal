package com.squarejobs.www.repo;

import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.entity.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepo extends CrudRepository <Offer, Long> {

    @Query("SELECT o FROM Offer o WHERE o.offerName = :offerName")
    public Optional<Offer> findOfferByOfferName(@Param("offerName") String offerName);

    Offer save(Offer offer);
}
