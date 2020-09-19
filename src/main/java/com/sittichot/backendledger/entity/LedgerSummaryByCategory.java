package com.sittichot.backendledger.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LedgerSummaryByCategory {
    private LedgerCategoryType type;
    private BigDecimal amount;
}
