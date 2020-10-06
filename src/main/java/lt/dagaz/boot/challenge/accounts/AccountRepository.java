package lt.dagaz.boot.challenge.accounts;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountDAO, String> {
}
