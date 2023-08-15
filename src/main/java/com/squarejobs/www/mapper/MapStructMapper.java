package com.squarejobs.www.mapper;

import com.squarejobs.www.DTO.OfferDTO;
import com.squarejobs.www.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Mapping(source = "company.pkCompany", target = "companyId")
    @Mapping(source = "introductionDate", target = "introductionDateDto") // напомни себе можно ли здесь не прописывать связку всех полей
    OfferDTO toOfferDTO(Offer offer);

    Offer toOffer(OfferDTO offerDTO);

    List<OfferDTO> toOfferDTOs(List<Offer> offers);

    List<Offer> toOffers(List<OfferDTO> offerDTOs);
}