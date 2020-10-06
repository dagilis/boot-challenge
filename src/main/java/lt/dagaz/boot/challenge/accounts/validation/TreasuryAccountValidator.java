package lt.dagaz.boot.challenge.accounts.validation;

import lt.dagaz.boot.challenge.accounts.model.Account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.math.BigDecimal.ZERO;

public class TreasuryAccountValidator implements ConstraintValidator<TreasuryAccount, Account> {

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext constraintValidatorContext) {
        return isNonTreasuryIsPositive(account);
    }

    private boolean isNonTreasuryIsPositive(Account account) {
        if (account.isTreasury() != null && !account.isTreasury()) {
            return account.getBalance() != null && account.getBalance().compareTo(ZERO) >= 0;
        }
        return true;
    }
}