package lt.dagaz.boot.challenge.transactions.validation;

import lt.dagaz.boot.challenge.accounts.AccountRepository;
import lt.dagaz.boot.challenge.accounts.model.Account;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import lt.dagaz.boot.challenge.transactions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.math.BigDecimal.ZERO;

public class ViableTransactionValidator implements ConstraintValidator<ViableTransaction, Transaction> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        AccountDAO from = accountRepository.findByName(transaction.getFromName());
        if (from == null) {
            return false;
        }
        AccountDAO to = accountRepository.findByName(transaction.getToName());
        if (to == null) {
            return false;
        }
        if (!from.getCurrency().equals(to.getCurrency())) {
            return false;
        }
        return !from.isTreasury()
                || from.getBalance().subtract(transaction.getAmount()).compareTo(ZERO) >= 0;
    }


}