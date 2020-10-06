package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.AccountRepository;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static java.util.stream.StreamSupport.stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void showsAllSampleAccountsFromApplicationClass() {
        Iterable<AccountDAO> allByName = accountRepository.findAll();

        assertThat(stream(allByName.spliterator(), false).count(), is(3L));
    }

    @Test
    void findsOneAccountWhenSpecificNameProvided() {
        AccountDAO specific = accountRepository.findByName("Vytautas");

        assertThat(specific.getName(), is("Vytautas"));
    }

    @Test
    void findsMultipleByNam() {
        Iterable<AccountDAO> allByName = accountRepository.findByNameContains("Ma");

        assertThat(stream(allByName.spliterator(), false).count(), is(equalTo(2L)));
    }

}