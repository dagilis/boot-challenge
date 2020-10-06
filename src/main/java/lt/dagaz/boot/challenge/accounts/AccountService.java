package lt.dagaz.boot.challenge.accounts;

import lt.dagaz.boot.challenge.accounts.model.Account;
import lt.dagaz.boot.challenge.accounts.model.AccountDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void save(Account account) {
        accountRepository.save(modelMapper.map(account, AccountDAO.class));
    }

    public List<Account> getByName(String name) {
        if (name != null) {
            return mapToDTO(accountRepository.findByName(name));
        }
        return mapToDTO(accountRepository.findAll());
    }

    private List<Account> mapToDTO(Iterable<AccountDAO> findResults) {
        return StreamSupport.stream(findResults.spliterator(), false)
                .map(accountDAO -> modelMapper.map(accountDAO, Account.class))
                .collect(toList());
    }
}
