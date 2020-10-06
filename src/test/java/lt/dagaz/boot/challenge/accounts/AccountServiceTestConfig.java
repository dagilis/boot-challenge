package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.model.Account;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Currency;

import static java.math.BigDecimal.TEN;

@TestConfiguration
class AccountServiceTestConfig {

    public static final AccountDAO SAMPLE_DTO = createSampleEntity();
    public static final Account SAMPLE_ACCOUNT = new Account("example", Currency.getInstance("EUR"), BigDecimal.valueOf(10.1), false);

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AccountService createAccountService() {
        return new AccountService();
    }

    static AccountDAO createSampleEntity() {
        AccountDAO dao = new AccountDAO();
        dao.setName("example");
        dao.setCurrency(Currency.getInstance("EUR"));
        dao.setBalance(TEN);
        dao.setTreasury(false);
        return dao;
    }
}
