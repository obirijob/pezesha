package com.example.pezesha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pezesha.entity.Transfer;

public interface TransferRepo extends JpaRepository<Transfer, Integer> {

}
