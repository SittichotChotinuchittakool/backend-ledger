package com.sittichot.backendledger.entity;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sittichot.backendledger.util.ClockUtils;

class LedgerRequestTest {
    private LedgerRequest underTest;

    @BeforeEach
    public void setup() {
        underTest = createLedgerRequest();
    }

    @Test
    public void testGetEntity() {
        Ledger result = underTest.getEntity();

        Assertions.assertEquals(BigDecimal.TEN, result.getAmount());
        Assertions.assertEquals(1L, result.getCreatedBy());
        Assertions.assertEquals(LedgerType.INCOME, result.getLedgerType());
        Assertions.assertEquals(ClockUtils.createDate(2020, 9, 4), result.getPaymentDate());
        Assertions.assertEquals("TEST", result.getMemo());
    }

    private LedgerRequest createLedgerRequest() {
        LedgerRequest req = new LedgerRequest();

        req.setAmount(BigDecimal.TEN);
        req.setCreatedBy(1L);
        req.setLedgerType(LedgerType.INCOME);
        req.setMemo("TEST");
        req.setPaymentDate(ClockUtils.createDate(2020, 9, 4));
        return req;
    }
}