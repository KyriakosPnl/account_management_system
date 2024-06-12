package com.panaliskyriakos.account_management_system.services;

import com.panaliskyriakos.account_management_system.models.Transaction;
import com.panaliskyriakos.account_management_system.repositories.TransactionRepository;
import com.panaliskyriakos.account_management_system.utils.TransactionDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    private static final String TRANSACTION_TYPE_DEPOSIT = "deposit";
    private static final String TRANSACTION_TYPE_WITHDRAWAL = "withdrawal";
    
    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        dto.setDate(transaction.getDate());
        return dto;
    }
    
    public List<TransactionDTO> getTransactionsByBeneficiaryId(Integer beneficiaryId){
        List<Transaction> transactions = transactionRepository.findByAccountBeneficiaryBeneficiaryId(beneficiaryId);
        
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public BigDecimal getBalanceByBeneficiaryId(Integer beneficiaryId) {
        BigDecimal balance = BigDecimal.ZERO;
        List<Transaction> transactions = transactionRepository.findByAccountBeneficiaryBeneficiaryId(beneficiaryId);
        
        for (Transaction transaction : transactions) {
            if (TRANSACTION_TYPE_DEPOSIT.equalsIgnoreCase(transaction.getType())) {
                balance = balance.add(transaction.getAmount());
            } else if (TRANSACTION_TYPE_WITHDRAWAL.equalsIgnoreCase(transaction.getType())) {
                balance = balance.subtract(transaction.getAmount());
            }
        }
        return balance;
    }
    
    public BigDecimal getLargestWithdrawalLastMonth(Integer beneficiaryId) {
        BigDecimal largestWithdrawal = BigDecimal.ZERO;
        LocalDate lastMonthStart = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastMonthEnd = LocalDate.now().withDayOfMonth(1).minusDays(1);

        // Find transactions within the last month
        List<Transaction> transactions = transactionRepository.findByAccountBeneficiaryBeneficiaryIdAndTypeAndDateBetween(
            beneficiaryId, TRANSACTION_TYPE_WITHDRAWAL, lastMonthStart.atStartOfDay(), lastMonthEnd.atTime(23, 59, 59)
        );

        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(largestWithdrawal) > 0) {
                largestWithdrawal = transaction.getAmount();
            }
        }

        return largestWithdrawal;
    }
    
}
