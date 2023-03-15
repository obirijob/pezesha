package com.example.pezesha.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "balance")
    private Double balance;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "from", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Transfer> debits;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "to", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Transfer> credits;
}
