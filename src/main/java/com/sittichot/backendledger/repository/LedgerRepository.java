package com.sittichot.backendledger.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sittichot.backendledger.entity.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, String> {

    List<Ledger> findAllByCreatedByAndPaymentDateOrderByCreatedTimeAsc(Long createdBy, Date paymentDate);
    List<Ledger> findAllByCreatedByOrderByCreatedTimeAsc(Long createdBy);
}
