package com.squarejobs.www.repo;

import com.squarejobs.www.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepo extends JpaRepository <Company, Long> {


    @Query("SELECT c FROM Company c WHERE c.nip = :nip")
    Company findCompanyByNipLaterDelete (@Param("nip") String nip);

    Company save(Company company);
}
