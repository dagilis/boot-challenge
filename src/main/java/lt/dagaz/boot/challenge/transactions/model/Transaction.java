package lt.dagaz.boot.challenge.transactions.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

public class Transaction {
    @NotBlank
    private String fromName;

    @NotBlank
    private String toName;

    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(String fromName, String toName, Currency currency, BigDecimal amount) {
        this.fromName = fromName;
        this.toName = toName;
        this.currency = currency;
        this.amount = amount;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
