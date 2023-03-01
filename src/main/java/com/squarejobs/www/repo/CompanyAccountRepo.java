package com.squarejobs.www.repo;

import com.squarejobs.www.entity.CompanyAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyAccountRepo extends CrudRepository <CompanyAccount, Long> {
    @Query("SELECT ca FROM CompanyAccount ca WHERE ca.email = :email")
    Optional<CompanyAccount> findCompanyAccountByEmail (@Param("email") String email);

    Optional save(Optional companyAccount);
}
