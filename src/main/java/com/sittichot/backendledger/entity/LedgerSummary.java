package com.sittichot.backendledger.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class LedgerSummary {
    private List<Ledger> ledgers;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal totalAmount;
    private List<LedgerSummaryByCategory> totalByCategory;

    public LedgerSummary(List<Ledger> ledgers) {
        this.ledgers = ledgers;
        this.totalIncome = calculateTotal(LedgerType.INCOME);
        this.totalExpense = calculateTotal(LedgerType.EXPENSE);
        this.totalAmount = totalIncome.subtract(totalExpense);
        this.totalByCategory = calculateTotalByType();
    }

    private List<LedgerSummaryByCategory> calculateTotalByType() {
        List<LedgerSummaryByCategory> totalByType = new ArrayList<>();

        for (LedgerCategoryType type : LedgerCategoryType.values()) {
            LedgerSummaryByCategory category = new LedgerSummaryByCategory();

            category.setType(type);
            category.setAmount(calculateTotalByCategory(ledgers, type));

            totalByType.add(category);
        }

        return totalByType;
    }

    private BigDecimal calculateTotalByCategory(List<Ledger> ledgers, LedgerCategoryType type) {
        return ledgers.stream()
                .filter(ledger -> ledger.getCategoryType() == type)
                .map(Ledger::getAmount)
                .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
    }

    private BigDecimal calculateTotal(LedgerType type) {
        return ledgers.stream()
                .filter(ledger -> ledger.getLedgerType() == type)
                .map(Ledger::getAmount)
                .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
    }
}
