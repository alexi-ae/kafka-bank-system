package com.alexiae.kafka.transaction.domain.enums;

public enum TransactionStatus {
    SUCCESS,
    ERROR,
    PROGRESS,
    PENDING,
    DEPOSITED;

    public static TransactionStatus getByKey(String key) {
        try {
            return TransactionStatus.valueOf(key.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ERROR;
        }
    }
}