package com.squarejobs.www.repo;

import com.squarejobs.www.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.nip = :nip")
    Optional<Company> findCompanyByNip (@Param("nip") String nip);

    Optional save(Optional company);
}
