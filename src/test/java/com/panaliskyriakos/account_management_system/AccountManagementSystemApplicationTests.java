package com.panaliskyriakos.account_management_system;

import com.panaliskyriakos.account_management_system.models.Beneficiary;
import com.panaliskyriakos.account_management_system.repositories.BeneficiaryRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AccountManagementSystemApplicationTests {

	@Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Test
    public void testFindAll() {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
        assertThat(beneficiaries).isNotNull();
        assertThat(beneficiaries).hasSizeGreaterThan(0);
    }
}
