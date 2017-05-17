package com.chahoo.u4d.api.accounts;

import com.chahoo.u4d.api.accounts.exception.AccountNotFoundException;
import com.chahoo.u4d.api.accounts.exception.UserDuplicatedException;
import com.chahoo.u4d.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by jjryu on 2017-02-07.
 */
@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public Account createAccount(AccountDto.Create dto) {
        Account account = modelMapper.map(dto, Account.class);

        String loginId = dto.getLoginId();
        if (repository.findByLoginId(loginId) != null) {
            //log.error("user duplicated exception. {}", username);
            throw new UserDuplicatedException(loginId);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Date now = new Date();
        account.setCreateDate(now);
        account.setUpdateDate(now);
        return repository.save(account);
    }

    public Account updateAccount(String loginId, AccountDto.Update updateDto) {
        Account account = getAccount(loginId);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setUserName(updateDto.getUserName());

        return repository.save(account);
    }

    public Account getAccount(String loginId) {
        Account account = repository.findByLoginId(loginId);
        if (account == null) {
            throw new AccountNotFoundException(loginId);
        }
        return account;
    }

    public void deleteAccount(String loginId) {
        repository.delete(getAccount(loginId));
    }
}
