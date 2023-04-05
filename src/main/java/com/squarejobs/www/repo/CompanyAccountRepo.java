package com.squarejobs.www.repo;

import com.squarejobs.www.entity.CompanyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyAccountRepo extends JpaRepository <CompanyAccount, Long> {
    @Query("SELECT ca FROM CompanyAccount ca WHERE ca.email = :email")
    CompanyAccount findCompanyAccountByEmail (@Param("email") String email);

    CompanyAccount save(CompanyAccount companyAccount);
}
