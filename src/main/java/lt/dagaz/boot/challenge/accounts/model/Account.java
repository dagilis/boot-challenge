package lt.dagaz.boot.challenge.accounts.model;

import lt.dagaz.boot.challenge.accounts.validation.TreasuryAccount;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@TreasuryAccount
public class Account {

    @NotBlank
    private String name;

    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Boolean treasury;

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

    public Boolean isTreasury() {
        return treasury;
    }

    public void setTreasury(boolean treasury) {
        this.treasury = treasury;
    }
}
