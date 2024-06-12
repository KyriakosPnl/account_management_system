package com.panaliskyriakos.account_management_system.services;

import com.panaliskyriakos.account_management_system.models.Beneficiary;
import com.panaliskyriakos.account_management_system.repositories.BeneficiaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    
    public Page<Beneficiary> getAllBeneficiaries(Pageable pageable){
        return beneficiaryRepository.findAll(pageable);
    }
    
    @Transactional
    public Beneficiary getBeneficiaryById(Integer beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new EntityNotFoundException("Couldn't find beneficiary with id: " + beneficiaryId));
    }
    
}
