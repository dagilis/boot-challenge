package lt.dagaz.boot.challenge.accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

@RestController
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return Collections.singletonList(new Account("example", Currency.getInstance("EUR"), BigDecimal.valueOf(10.1), false));
    }

}