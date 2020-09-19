package com.sittichot.backendledger.entity;

import java.io.Serializable;

public abstract class AbstractEntity<I extends Serializable> {
    abstract protected void setId(I id);
    abstract public I getId();

    public static <I extends Serializable> void setId(AbstractEntity<I> e, I id) {
        e.setId(id);
    }
}
