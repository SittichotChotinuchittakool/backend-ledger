package com.sittichot.backendledger.service;

import javax.persistence.AttributeConverter;

public interface ColumnEncryptor<X, Y> extends AttributeConverter<X, Y> {
}
