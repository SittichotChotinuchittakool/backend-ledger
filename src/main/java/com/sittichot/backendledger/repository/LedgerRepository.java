package com.sittichot.backendledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sittichot.backendledger.entity.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, String> {
}
