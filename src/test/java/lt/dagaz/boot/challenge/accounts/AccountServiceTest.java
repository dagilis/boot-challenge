package lt.dagaz.boot.challenge.accounts;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Currency;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class AccountServiceTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ModelMapper createModelMapper() {
            return new ModelMapper();
        }

        @Bean
        public AccountService createAccountService() {
            return new AccountService();
        }
    }

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
        AccountDAO sampleEntity = createSampleEntity();
        given(accountRepository.findAll())
                .willReturn(singletonList(sampleEntity));

        List<Account> accounts = accountService.getAccounts();

        assertEquals(accounts.size(), 1, "Database should return exactly 1 item");
        assertEquals(accounts.get(0).getBalance(), sampleEntity.getBalance(), "Value should match initial one");
        assertEquals(accounts.get(0).getName(), sampleEntity.getName(), "Value should match initial one");
        assertEquals(accounts.get(0).getCurrency(), sampleEntity.getCurrency(), "Value should match initial one");
        assertEquals(accounts.get(0).isTreasury(), sampleEntity.isTreasury(), "Value should match initial one");
    }

    private AccountDAO createSampleEntity() {
        AccountDAO dao = new AccountDAO();
        dao.setName("Sample");
        dao.setCurrency(Currency.getInstance("EUR"));
        dao.setBalance(TEN);
        dao.setTreasury(false);
        return dao;
    }
}