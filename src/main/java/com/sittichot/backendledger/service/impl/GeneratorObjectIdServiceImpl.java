package com.sittichot.backendledger.service.impl;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.util.DigestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorObjectIdServiceImpl implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        UUID uuid = UUID.randomUUID();
        String md5Hex = DigestUtils.md5DigestAsHex(uuid.toString().getBytes());
        return md5Hex;
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }
}
