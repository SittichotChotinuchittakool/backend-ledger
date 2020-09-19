package com.sittichot.backendledger.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sittichot.backendledger.util.ClockUtils;

import lombok.Data;

@Data
public class LedgerRequest implements EntityRequest<Ledger> {
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LedgerType ledgerType;
    private String memo;
    @JsonIgnore
    private Long createdBy;
    @NotNull
    private Date paymentDate;
    @JsonProperty("category")
    @NotNull
    private LedgerCategoryType categoryType;

    @Override
    public Ledger getEntity() {
        Ledger ledger = new Ledger();

        ledger.setAmount(getAmount());
        ledger.setLedgerType(getLedgerType());
        ledger.setMemo(getMemo());
        ledger.setCreatedBy(getCreatedBy());
        ledger.setCreatedTime(ClockUtils.now());
        ledger.setPaymentDate(getPaymentDate());
        ledger.setCategoryType(getCategoryType());

        return ledger;
    }
}
