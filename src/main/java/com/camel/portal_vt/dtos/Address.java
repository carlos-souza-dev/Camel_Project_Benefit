package com.camel.portal_vt.dtos;

import java.util.Date;

public record Address(
        long addressId,
        String street,
        int number,
        String city,
        String district,
        String state,
        String uf,
        String cep,
        Date dateCreated,
        Date dateUpdated
) {}
