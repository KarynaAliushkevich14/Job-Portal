package com.squarejobs.www.mapper;

import com.squarejobs.www.DTO.CompanyAccountDTO;
import com.squarejobs.www.DTO.CompanyDTO;
import com.squarejobs.www.DTO.OfferDTO;
import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.CompanyAccount;
import com.squarejobs.www.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    // source = Entity (находишь объект другого класса Сompany; заходить в объект company и ищешь Long id); target = DTO (находишь id этого DTO)
    @Mapping(source = "company.pkCompany", target = "companyId")
    @Mapping(source = "introductionDate", target = "introductionDateDto") // напомни себе можно ли здесь не прописывать связку всех полей
    OfferDTO toOfferDTO(Offer offer);
    List<OfferDTO> toOfferDTOs(List<Offer> offers);
    Offer toOffer(OfferDTO offerDTO);
    List<Offer> toOffers(List<OfferDTO> offerDTOs);

    // Company
    @Mapping(source = "companyAccount.pkAccount", target = "companyAccountId") // здесь проблема?
    CompanyDTO toCompanyDTO (Company company);
    List<CompanyDTO> toCompanyDTOs (List<Company> companies);
    Company toCompany (CompanyDTO companyDTO); // Reverse mapping
    List<Company> toCompanies (List<CompanyDTO> companyDTOs); // Reverse mapping

    // CompanyAccount
    CompanyAccountDTO toCompanyAccountDTO (CompanyAccount companyAccount);
    List <CompanyAccountDTO> toCompanyAccountDTOs (List <CompanyAccount> companyAccounts);
    CompanyAccount toCompanyAccount (CompanyAccountDTO companyAccountDTO); // Reverse mapping
    List <CompanyAccount> toCompanyAccounts (List<CompanyAccountDTO> companyAccountDTOs);// Reverse mapping
}