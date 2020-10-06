package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.model.Account;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static java.util.Collections.singletonList;
import static lt.dagaz.boot.challenge.accounts.AccountServiceTestConfig.SAMPLE_ACCOUNT;
import static lt.dagaz.boot.challenge.accounts.AccountServiceTestConfig.SAMPLE_DTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void getAccountsOnEmptyDbShouldBeEmpty() {
        List<Account> accounts = accountService.getByName(null);

        assertThat(accounts, is(empty()));
    }

    @Test
    public void mapperMapsEntityFromDbCorrectly() {
        given(accountRepository.findAll())
                .willReturn(singletonList(SAMPLE_DTO));

        List<Account> accounts = accountService.getByName(null);

        assertEquals(accounts.size(), 1, "Database should return exactly 1 item");
        assertEquals(accounts.get(0).getBalance(), SAMPLE_DTO.getBalance(), "Value should match initial one");
        assertEquals(accounts.get(0).getName(), SAMPLE_DTO.getName(), "Value should match initial one");
        assertEquals(accounts.get(0).getCurrency(), SAMPLE_DTO.getCurrency(), "Value should match initial one");
        assertEquals(accounts.get(0).isTreasury(), SAMPLE_DTO.isTreasury(), "Value should match initial one");
    }

    @Test
    public void findByNameMapsCorrectly() {
        given(accountRepository.findByNameContains(anyString()))
                .willReturn(singletonList(SAMPLE_DTO));

        List<Account> accounts = accountService.getByName("example");

        assertEquals(1, accounts.size(), "Database should return exactly 1 item");
        assertEquals(SAMPLE_DTO.getName(), accounts.get(0).getName(), "Value should match initial one");
    }

    @Test
    public void callingServiceEntityIsStoredIntoDB() {
        ArgumentCaptor<AccountDAO> argumentCaptor = ArgumentCaptor.forClass(AccountDAO.class);
        when(accountRepository.save(argumentCaptor.capture())).thenReturn(SAMPLE_DTO);

        accountService.save(SAMPLE_ACCOUNT);

        AccountDAO value = argumentCaptor.getValue();
        assertThat(value.getName(), equalTo(SAMPLE_ACCOUNT.getName()));
    }
}