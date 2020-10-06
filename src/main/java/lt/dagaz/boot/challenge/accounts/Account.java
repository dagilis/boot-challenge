package lt.dagaz.boot.challenge.accounts;

import java.math.BigDecimal;
import java.util.Currency;

public class Account {
    private String name;
    private Currency currency;
    private BigDecimal balance;
    private boolean treasury;

    public Account() {
    }

    public Account(String name, Currency currency, BigDecimal balance, boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isTreasury() {
        return treasury;
    }

    public void setTreasury(boolean treasury) {
        this.treasury = treasury;
    }
}
