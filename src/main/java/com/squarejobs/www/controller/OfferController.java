package com.squarejobs.www.controller;

import com.squarejobs.www.DTO.OfferDTO;
import com.squarejobs.www.entity.Company;
import com.squarejobs.www.entity.Offer;
import com.squarejobs.www.exceptions.CompanyIsAlreadyExistException;
import com.squarejobs.www.mapper.MapStructMapper;
import com.squarejobs.www.service.CompanyService;
import com.squarejobs.www.service.OfferService;
import com.squarejobs.www.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OfferController {

    private static final Logger log =  LoggerFactory.getLogger(OfferController.class);

    private final OfferService offerService;
    private final CompanyService companyService;
    private final MapStructMapper mapStructMapper;


    @Autowired
    public OfferController(OfferService offerService, CompanyService companyService, MapStructMapper mapStructMapper) {
        this.offerService = offerService;
        this.companyService = companyService;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping(value="/getOffer/{city}")
    public ResponseEntity<OfferDTO> getOffer (@PathVariable String city) {
        OfferDTO offerDTO = mapStructMapper.toOfferDTO(offerService.findOfferByOfferCity(city));
        return ResponseEntity.ok(offerDTO);
    }

    @GetMapping(value="/getAllOffers")
    public ResponseEntity<List<OfferDTO>> getAllOffers () {
        List<OfferDTO> offerDTOs = mapStructMapper.toOfferDTOs(offerService.findAllOffers());
        return ResponseEntity.ok(offerDTOs);
    }

    @PostMapping(value="/saveOffer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveOffer (@RequestBody Offer inputOffer) {
        try {
            // проверка из Сессии, что это тот CompanyAccount который нам нужен
            /**ПОКА ЧТО УСТАНОВЛЮ В РУЧНУЮ CompanyName, Nip, НО ПОСЛЕ SESSION ДОЛЖЕН ВПИХИВАТЬ ЭТИ ПОЛЯ*/
            Company BIGCompany = companyService.getCompanyByNipLaterDelete("00000001");
            inputOffer.getCompany().setCompanyName(BIGCompany.getCompanyName());
            inputOffer.getCompany().setNip(BIGCompany.getNip());
            inputOffer.getCompany().setPkCompany(BIGCompany.getPkCompany());
            inputOffer.getCompany().setIntroductionDate(CommonUtils.getCurrentTime());

            inputOffer.setIntroductionDate(CommonUtils.getCurrentTime());

            offerService.saveOffer(inputOffer);
        } catch (CompanyIsAlreadyExistException | IOException ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
