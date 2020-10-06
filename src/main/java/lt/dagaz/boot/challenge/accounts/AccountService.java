package lt.dagaz.boot.challenge.accounts;

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

    public List<Account> getAccounts() {
        return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
                .map(accountDAO -> modelMapper.map(accountDAO, Account.class))
                .collect(toList());

    }
}