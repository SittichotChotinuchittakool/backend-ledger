package com.sittichot.backendledger.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LedgerSummaryTest {
    private LedgerSummary underTest;
    private List<Ledger> ledgers;

    @BeforeEach
    public void setup() {
        ledgers = new ArrayList<>();
    }

    @Test
    public void testLedgerSummaryWithIncomeAndExpense() {
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(1000)));
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(1250)));
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(250)));
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(70)));

        underTest = new LedgerSummary(ledgers);

        Assertions.assertEquals(BigDecimal.valueOf(2250).setScale(2), underTest.getTotalIncome());
        Assertions.assertEquals(BigDecimal.valueOf(320).setScale(2), underTest.getTotalExpense());
        Assertions.assertEquals(BigDecimal.valueOf(1930).setScale(2), underTest.getTotalAmount());
    }

    @Test
    public void testLedgerSummaryWithIncome() {
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(1000)));
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(1250)));
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(500)));
        ledgers.add(createLedger(LedgerType.INCOME, BigDecimal.valueOf(50)));

        underTest = new LedgerSummary(ledgers);

        Assertions.assertEquals(BigDecimal.valueOf(2800).setScale(2), underTest.getTotalIncome());
        Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), underTest.getTotalExpense());
        Assertions.assertEquals(BigDecimal.valueOf(2800).setScale(2), underTest.getTotalAmount());
    }

    @Test
    public void testLedgerSummaryWithExpense() {
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(1000)));
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(1250)));
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(500)));
        ledgers.add(createLedger(LedgerType.EXPENSE, BigDecimal.valueOf(50)));

        underTest = new LedgerSummary(ledgers);

        Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), underTest.getTotalIncome());
        Assertions.assertEquals(BigDecimal.valueOf(2800).setScale(2), underTest.getTotalExpense());
        Assertions.assertEquals(BigDecimal.valueOf(-2800).setScale(2), underTest.getTotalAmount());
    }

    private Ledger createLedger(LedgerType type, BigDecimal amount) {
        Ledger ledger = new Ledger();

        ledger.setLedgerType(type);
        ledger.setAmount(amount);

        return ledger;
    }

}