package com.camel.portal_vt.dtos;

public record Address(
        String street,
        int number,
        String city,
        String district,
        String state,
        String uf,
        String cep
) {}
