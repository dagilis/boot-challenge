package lt.dagaz.boot.challenge.transactions;

import lt.dagaz.boot.challenge.accounts.AccountRepository;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import lt.dagaz.boot.challenge.transactions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transfer(Transaction transaction) {
        AccountDAO from = accountRepository.findByName(transaction.getFromName());
        AccountDAO to = accountRepository.findByName(transaction.getToName());
        from.setBalance(from.getBalance().subtract(transaction.getAmount()));
        to.setBalance(to.getBalance().add(transaction.getAmount()));
        accountRepository.save(from);
        accountRepository.save(to);
    }
}
