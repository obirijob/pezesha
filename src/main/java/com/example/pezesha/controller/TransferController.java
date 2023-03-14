package com.example.pezesha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import com.example.pezesha.service.TransferService;
import com.example.pezesha.entity.Transfer;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfers")
    public ResponseEntity<?> makeTransfer(@RequestBody Transfer transfer) {
        // make the transfer action
        return transferService.makeTransfer(transfer);
    }
}
