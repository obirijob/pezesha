package com.example.pezesha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pezesha.entity.Account;
import com.example.pezesha.repository.AccountRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public Account newAccount(Account account) {
        return accountRepo.save(account);
    }

    public Account getAccount(int id) {
        // checks the db to find the account else it will return null

        return accountRepo.findById(id).orElse(null);
    }

    public Account transact(int id, double amount) {
        // refetches the account info - for the latest info
        Account selected = accountRepo.findById(id).orElse(null);
        double currentBalance = selected.getBalance();
        double newBalance = currentBalance + amount;
        selected.setBalance(newBalance);
        accountRepo.save(selected);
        // returns the newly updated account
        return selected;
    }

}
