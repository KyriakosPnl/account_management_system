package com.panaliskyriakos.account_management_system.services;

import com.panaliskyriakos.account_management_system.models.Account;
import com.panaliskyriakos.account_management_system.repositories.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountsByBeneficiaryId(Integer beneficiaryId) {
        return accountRepository.findByBeneficiaryBeneficiaryId(beneficiaryId);
    }	
}
