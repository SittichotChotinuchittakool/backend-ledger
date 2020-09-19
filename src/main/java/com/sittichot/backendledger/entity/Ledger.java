package com.sittichot.backendledger.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity(name = "ledger_info")
public class Ledger extends AbstractEntity<String> {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",
            strategy = "com.sittichot.backendledger.service.impl.GeneratorObjectIdServiceImpl")
    private String id;
    private BigDecimal amount;
    private String memo;
    private LedgerType ledgerType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long createdBy;
    private Date createdTime;
    private Date paymentDate;
    private LedgerCategoryType categoryType;
    @Version
    private int version;
}
