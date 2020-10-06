package lt.dagaz.boot.challenge.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "accounts")
public class AccountDAO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Currency currency;

    private BigDecimal balance;

    private boolean treasury;

    public AccountDAO() {
    }

    public AccountDAO(String name, Currency currency, BigDecimal balance, boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AccountDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", treasury=" + treasury +
                '}';
    }
}
