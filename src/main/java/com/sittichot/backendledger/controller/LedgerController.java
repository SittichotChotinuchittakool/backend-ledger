package com.sittichot.backendledger.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sittichot.backendledger.entity.FilterDate;
import com.sittichot.backendledger.entity.Ledger;
import com.sittichot.backendledger.entity.LedgerRequest;
import com.sittichot.backendledger.entity.LedgerSummary;
import com.sittichot.backendledger.exception.ForbiddenException;
import com.sittichot.backendledger.exception.ResourceNotFoundException;
import com.sittichot.backendledger.repository.LedgerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/ledger/{userId}")
public class LedgerController {
    @Autowired
    private LedgerRepository ledgerRepository;

    @GetMapping("/{id}")
    public Ledger getLedgerById(Authentication authentication, @PathVariable Long userId, @PathVariable String id) {
        verifyAuthentication(userId, authentication);

        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if (ledger.isEmpty()) {
            throw new ResourceNotFoundException(Ledger.class);
        }
        return ledger.get();
    }

    @GetMapping
    public LedgerSummary getLedgerByUser(Authentication authentication, @PathVariable Long userId, FilterDate filterDate) {
        verifyAuthentication(userId, authentication);

        log.info("filterDate={}, getDate={}", filterDate, filterDate.getDate());
        List<Ledger> ledgers = ledgerRepository.findAllByCreatedByAndPaymentDateOrderByCreatedTimeAsc(userId, filterDate.getDate());
        return new LedgerSummary(ledgers);
    }

    @PostMapping
    public Ledger createLedger(Authentication authentication, @PathVariable Long userId, @Valid @RequestBody LedgerRequest req) {
        verifyAuthentication(userId, authentication);

        req.setCreatedBy(userId);
        log.info("req={}", req);
        return ledgerRepository.save(req.getEntity());
    }

    @Retryable(
            value = {StaleObjectStateException.class}
    )
    @PutMapping("/{id}")
    public Ledger updateLedger(Authentication authentication, @PathVariable Long userId,@PathVariable String id, @RequestBody LedgerRequest body) {
        verifyAuthentication(userId, authentication);

        Optional<Ledger> optionalLedger = ledgerRepository.findById(id);
        if (optionalLedger.isEmpty()) {
            throw new ResourceNotFoundException(Ledger.class);
        }
        Ledger ledger = optionalLedger.get();
        ledger.setAmount(body.getAmount());
        Ledger newLedger = ledgerRepository.save(ledger);

        return newLedger;
    }

    private void verifyAuthentication(Long userId, Authentication authentication) {
        if (!userId.toString().equals(authentication.getName())) {
            throw new ForbiddenException(userId);
        }
    }
}
