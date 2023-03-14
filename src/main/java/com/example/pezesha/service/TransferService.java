package com.example.pezesha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pezesha.repository.TransferRepo;
import com.example.pezesha.service.AccountService;
import com.example.pezesha.entity.Account;
import com.example.pezesha.entity.Transfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class TransferService {

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> makeTransfer(Transfer transfer) {
        // gets the values from the transfer object
        int from = transfer.getFrom();
        int to = transfer.getTo();
        double amount = transfer.getAmount();

        // loads their most recent info
        Account fromAccount = accountService.getAccount(from);
        Account toAccount = accountService.getAccount(to);

        // checks if amount is less than zero
        if (amount <= 0) {
            return new ResponseEntity<>("Invalid Transfer Amount. Amount must be more than 0", HttpStatus.BAD_REQUEST);
        }

        // checks if the account ids entered have an account
        if (fromAccount == null) {
            return new ResponseEntity<>("Account " + from + " not found", HttpStatus.NOT_FOUND);
        }

        if (toAccount == null) {
            return new ResponseEntity<>("Account " + to + " not found", HttpStatus.NOT_FOUND);
        }

        // check the balance where it is going to be deducted from
        double fromBalance = fromAccount.getBalance();

        // if the amount is more than the balance it returns a bad request
        if (fromBalance < amount) {
            return new ResponseEntity<>("Insufficient Balance on Account " + from, HttpStatus.BAD_REQUEST);
        }

        // create a transfer record
        Transfer transferRecord = new Transfer();
        transferRecord.setAmount(amount);
        transferRecord.setFrom(from);
        transferRecord.setTo(to);

        // save the transfered record
        Transfer transfered = transferRepo.save(transferRecord);

        // perform the transaction math on the accounts
        accountService.transact(from, -amount);
        accountService.transact(to, +amount);

        return new ResponseEntity<Transfer>(transfered, HttpStatus.OK);

    }

}
