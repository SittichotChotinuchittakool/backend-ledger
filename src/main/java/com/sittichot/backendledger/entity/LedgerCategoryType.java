package com.sittichot.backendledger.entity;

import java.util.HashMap;
import java.util.Map;

public enum LedgerCategoryType {
    FOOD(0),
    TRAVEL(1),
    SALARY(2),
    TRAVEL_EXPENSES(3),
    OTHER(4);

    private int value;
    private static final Map<Integer, LedgerCategoryType> ALL = getAllValue();

    public static Map<Integer, LedgerCategoryType> getAllValue() {
        Map<Integer, LedgerCategoryType> categoryTypeMap = new HashMap<>();
        for(LedgerCategoryType value : LedgerCategoryType.values()) {
            categoryTypeMap.put(value.getValue(), value);
        }
        return categoryTypeMap;
    }

    LedgerCategoryType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
