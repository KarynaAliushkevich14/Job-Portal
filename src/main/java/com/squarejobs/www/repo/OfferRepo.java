package com.squarejobs.www.repo;

import com.squarejobs.www.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepo extends JpaRepository <Offer, Long> {

    @Query("SELECT o FROM Offer o WHERE o.offerName = :offerName")
    public Offer findOfferByOfferName(@Param("offerName") String offerName);

    @Query("SELECT o FROM Offer o WHERE o.city = :city")
    public Offer findOfferByOfferCity(@Param("city") String city);

    @Query("SELECT o FROM Offer o JOIN o.technologies t WHERE t.technologyName = :technology")
    public List<Offer> findOffersByTechnology (@Param("technology") String technology);

    Offer save(Offer offer);
    //@Query("SELECT o FROM Offer o")
    List<Offer> findAll();
}
