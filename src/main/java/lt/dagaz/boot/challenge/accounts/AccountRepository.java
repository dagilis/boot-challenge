package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountDAO, String> {

    Iterable<AccountDAO> findByNameContains(String name);

    AccountDAO findByName(String name);

}