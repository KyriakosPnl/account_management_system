
package com.panaliskyriakos.account_management_system.repositories;

import com.panaliskyriakos.account_management_system.models.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    List <Transaction> findByAccountBeneficiaryBeneficiaryId(Integer beneficiaryId);
    
    List<Transaction> findByAccountBeneficiaryBeneficiaryIdAndTypeAndDateBetween(
            Integer beneficiaryId, String type, LocalDateTime startDate, LocalDateTime endDate
    );
    
}
