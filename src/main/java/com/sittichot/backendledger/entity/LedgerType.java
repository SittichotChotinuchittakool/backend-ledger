package com.sittichot.backendledger.entity;

import java.util.HashMap;
import java.util.Map;

public enum LedgerType{
    INCOME(0),
    EXPENSE(1);

    private int value;
    private static final Map<Integer, LedgerType> ALL = getAllValue();

    public static Map<Integer, LedgerType> getAllValue() {
        Map<Integer, LedgerType> ledgerTypeMap = new HashMap<>();
        for(LedgerType value : LedgerType.values()) {
            ledgerTypeMap.put(value.getValue(), value);
        }
        return ledgerTypeMap;
    }

    LedgerType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
