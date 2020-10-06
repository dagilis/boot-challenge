package lt.dagaz.boot.challenge.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static java.util.Collections.singletonList;
import static lt.dagaz.boot.challenge.accounts.AccountServiceTestConfig.SAMPLE_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void getAccountsOnEmptyDbShouldBeEmpty() {
        List<Account> accounts = accountService.getAccounts();

        assertTrue(accounts.isEmpty());
    }

    @Test
    public void mapperMapsEntityFromDbCorrectly() {
        given(accountRepository.findAll())
                .willReturn(singletonList(SAMPLE_DTO));

        List<Account> accounts = accountService.getAccounts();

        assertEquals(accounts.size(), 1, "Database should return exactly 1 item");
        assertEquals(accounts.get(0).getBalance(), SAMPLE_DTO.getBalance(), "Value should match initial one");
        assertEquals(accounts.get(0).getName(), SAMPLE_DTO.getName(), "Value should match initial one");
        assertEquals(accounts.get(0).getCurrency(), SAMPLE_DTO.getCurrency(), "Value should match initial one");
        assertEquals(accounts.get(0).isTreasury(), SAMPLE_DTO.isTreasury(), "Value should match initial one");
    }

}