package com.example.pezesha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.pezesha.entity.Account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.pezesha.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> postAccount(@RequestBody Account account) {
        // checks the starting balance if is less than zero then the account is not
        // created
        if (account.getBalance() < 0) {
            return new ResponseEntity<String>("Invalid Balance: " + account.getBalance(), HttpStatus.NOT_ACCEPTABLE);
        }
        // create new account service is called after validating
        Account newAccount = accountService.newAccount(account);
        return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Integer id) {
        Account found = accountService.getAccount(id);
        // checks if no account is found then returns 404 response
        if (found == null) {
            return new ResponseEntity<String>("Account of id " + id + " was not found!", HttpStatus.NOT_FOUND);
        }
        // if account is found it is returned through a 200 response
        return new ResponseEntity<Account>(found, HttpStatus.OK);
    }

}
