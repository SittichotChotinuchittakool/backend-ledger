package com.sittichot.backendledger.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sittichot.backendledger.entity.Ledger;
import com.sittichot.backendledger.entity.LedgerRequest;
import com.sittichot.backendledger.exception.ResourceNotFoundException;
import com.sittichot.backendledger.repository.LedgerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/ledger")
public class LedgerController {
    @Autowired
    private LedgerRepository ledgerRepository;

    @GetMapping("/{id}")
    public Ledger getLedgerById(@PathVariable String id) {
        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if (ledger.isEmpty()) {
            throw new ResourceNotFoundException(Ledger.class);
        }
        return ledger.get();
    }

    @PostMapping
    public Ledger createLedger(@RequestBody LedgerRequest req) {
        log.info("req={}", req);
        return ledgerRepository.save(req.getEntity());
    }
}
