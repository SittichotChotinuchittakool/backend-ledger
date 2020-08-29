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
@Entity(name = "ledger_info")
public class Ledger {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",
            strategy = "com.sittichot.backendledger.service.impl.GeneratorObjectIdServiceImpl")
    @Column(columnDefinition = "VARCHAR(32)")
    private String id;

    @Column(columnDefinition = "DECIMAL(19,2)")
    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(columnDefinition = "TINYINT")
    private LedgerType ledgerType;

//    @Column(columnDefinition = "DATETIME(6)", name = "created_time")
//    private Date createdTime;
}
