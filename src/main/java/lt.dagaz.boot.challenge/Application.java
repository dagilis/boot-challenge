package lt.dagaz.boot.challenge;

import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import lt.dagaz.boot.challenge.accounts.AccountRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Currency;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, AccountRepository accountRepository) {
        return args -> {

            log.info("Inserting initial data for easy testing.");
            accountRepository.save(new AccountDAO("Vytautas", Currency.getInstance("EUR"), BigDecimal.valueOf(2.3), false));
            accountRepository.save(new AccountDAO("Marie", Currency.getInstance("EUR"), BigDecimal.TEN, true));
            accountRepository.save(new AccountDAO("Mark", Currency.getInstance("USD"), BigDecimal.TEN, false));
            accountRepository.findAll().forEach(accountDAO -> log.info("sample:" + accountDAO.toString()));
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
