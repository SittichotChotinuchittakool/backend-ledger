package com.sittichot.backendledger.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


@Data
@Entity
public class Ledger {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",
            strategy = "com.sittichot.backendledger.service.impl.GeneratorObjectIdServiceImpl")
    private String id;
    private BigDecimal amount;
    @Column(columnDefinition = "TEXT")
    private String memo;
    private LedgerType ledgerType;
}
