package com.versus.www.repo;

import com.versus.www.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company as c WHERE c.nip = ?1")
    Optional<Company> findCompanyByNip (String nip);

}
