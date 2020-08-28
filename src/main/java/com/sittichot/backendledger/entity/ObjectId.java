package com.sittichot.backendledger.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ObjectId implements Serializable {
    private final byte[] src;
//    private transient String str;

    public ObjectId(byte[] src) {
        this.src = src;
    }
//    private byte[] id;
}
