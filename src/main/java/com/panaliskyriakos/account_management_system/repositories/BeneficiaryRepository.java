package com.panaliskyriakos.account_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.panaliskyriakos.account_management_system.models.Beneficiary;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer>{
    Optional<Beneficiary> findById(Long beneficiaryId);
    
    Page<Beneficiary> findAll(Pageable pageable);
}
