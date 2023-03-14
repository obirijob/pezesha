package com.example.pezesha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pezesha.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
