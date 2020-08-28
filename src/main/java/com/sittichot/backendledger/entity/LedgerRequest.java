package com.sittichot.backendledger.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LedgerRequest implements EntityRequest<Ledger> {
    private BigDecimal amount;
    private LedgerType ledgerType;
    private String memo;

    @Override
    public Ledger getEntity() {
        Ledger ledger = new Ledger();
        ledger.setAmount(getAmount());
        ledger.setLedgerType(getLedgerType());
        ledger.setMemo(getMemo());

        return ledger;
    }
}
