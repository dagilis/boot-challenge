package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccounts(@RequestParam(required = false) String name) {
        return accountService.getByName(name);
    }

    @PostMapping("/accounts")
    public void createAccount(@RequestBody @Valid Account account) {
        accountService.save(account);
    }
}