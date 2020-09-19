package com.sittichot.backendledger.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sittichot.backendledger.service.impl.ColumnEncryptorImpl;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "user_info")
public class User {
    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Convert(converter = ColumnEncryptorImpl.class)
    private String password;
    @Email
    private String email;
}
