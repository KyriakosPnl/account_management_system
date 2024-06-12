/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.panaliskyriakos.account_management_system.repositories;

import com.panaliskyriakos.account_management_system.models.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    List<Account> findByBeneficiaryBeneficiaryId(Integer beneficiaryId);
}
