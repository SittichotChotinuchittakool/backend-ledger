package com.sittichot.backendledger.service.impl;

public final class Collection {
    private Collection() {
    }

    public static byte[] fromNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Collection number must be positive");
        } else {
            return new byte[]{(byte) (number >> 8 & 255), (byte) (number & 255)};
        }
    }

    static int fromByte(byte num) {
        return num & 255;
    }
}
