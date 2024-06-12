package com.panaliskyriakos.account_management_system.controllers;

import com.panaliskyriakos.account_management_system.models.Account;
import com.panaliskyriakos.account_management_system.models.Beneficiary;
import com.panaliskyriakos.account_management_system.services.AccountService;
import com.panaliskyriakos.account_management_system.services.BeneficiaryService;
import com.panaliskyriakos.account_management_system.services.TransactionService;
import com.panaliskyriakos.account_management_system.utils.TransactionDTO;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/beneficiaries")
@Validated
public class BeneficiaryController {
    private static final String LOGGER_DEFAULT_STR_ENTERING_METHOD = "BeneficiaryController: Received request to /api/";
    private static final String VALIDATION_MSG ="Beneficiary ID cannot be null";
    private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
    
    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping
    public ResponseEntity<Page<Beneficiary>> getAllBeneficiaries(Pageable pageable){
        Page<Beneficiary> beneficiaries = beneficiaryService.getAllBeneficiaries(pageable);
        
        return ResponseEntity.ok().body(beneficiaries);
    }
    
    @GetMapping("/{beneficiaryId}")
    public ResponseEntity<Beneficiary> getBeneficiaryById(@PathVariable @NotNull(message = VALIDATION_MSG) Integer beneficiaryId) {
        logger.info(LOGGER_DEFAULT_STR_ENTERING_METHOD +beneficiaryId);
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        
        if (beneficiary != null) {
            return ResponseEntity.ok(beneficiary);
        }
        
        logger.warn("Didn't find beneficiary with ID " + beneficiaryId);
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{beneficiaryId}/accounts")
    public ResponseEntity<List<Account>> getAccountsByBeneficiaryId(@PathVariable @NotNull(message = VALIDATION_MSG) Integer beneficiaryId) {
        logger.info(LOGGER_DEFAULT_STR_ENTERING_METHOD +beneficiaryId +"/accounts");
        List<Account> accounts = accountService.getAccountsByBeneficiaryId(beneficiaryId);
        
        if (accounts != null && !accounts.isEmpty()) {
            return ResponseEntity.ok(accounts);
        }
        
        logger.warn("Didn't find accounts for beneficiary: " + beneficiaryId);
        return ResponseEntity.notFound().build();
        
    }
    
    @GetMapping("/{beneficiaryId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByBeneficiaryId(@PathVariable @NotNull(message = VALIDATION_MSG) Integer beneficiaryId) {
        logger.info(LOGGER_DEFAULT_STR_ENTERING_METHOD +beneficiaryId +"/transactions");
        List<TransactionDTO> transactions = transactionService.getTransactionsByBeneficiaryId(beneficiaryId);
        
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        }
        
        logger.warn("Didn't find accounts for beneficiary: " + beneficiaryId);
        return ResponseEntity.notFound().build();
        
    }
    
    @GetMapping("/{beneficiaryId}/balance")
    public ResponseEntity<Map<String, BigDecimal>> getBalanceByBeneficiaryId(@PathVariable @NotNull(message = VALIDATION_MSG) Integer beneficiaryId) {
        logger.info(LOGGER_DEFAULT_STR_ENTERING_METHOD +beneficiaryId+ "/balance");
        Map<String, BigDecimal> response = new HashMap<>();
        
        BigDecimal balance = transactionService.getBalanceByBeneficiaryId(beneficiaryId);
        if(balance != null) {
            response.put("balance", balance);
            return ResponseEntity.ok(response);
        }
        
        logger.warn("Didn't find balance for beneficiary: " + beneficiaryId);
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{beneficiaryId}/largest-withdrawal-last-month")
    public ResponseEntity<Map<String, BigDecimal>> getLargestWithdrawalLastMonth(@PathVariable @NotNull(message = VALIDATION_MSG) Integer beneficiaryId) {
        logger.info(LOGGER_DEFAULT_STR_ENTERING_METHOD +beneficiaryId +"/largest-withdrawal-last-month");
        Map<String, BigDecimal> response = new HashMap<>();
        
        BigDecimal largestWithdrawal = transactionService.getLargestWithdrawalLastMonth(beneficiaryId);
        if(largestWithdrawal != null) {
            response.put("largestWithdrawalLastMonth", largestWithdrawal);
            return ResponseEntity.ok(response);
        }
        
        logger.warn("Didn't find largest withdrawal for beneficiary: " + beneficiaryId);
        return ResponseEntity.notFound().build();
       
    }

}
