package lt.dagaz.boot.challenge.transactions;

import lt.dagaz.boot.challenge.transactions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public void transferMoney(@RequestBody @Valid Transaction transaction) {
        transactionService.transfer(transaction);
    }
}
