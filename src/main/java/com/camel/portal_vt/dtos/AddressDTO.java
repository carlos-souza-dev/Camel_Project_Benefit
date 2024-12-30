package com.camel.portal_vt.dtos;

public record AddressDTO(
        String street,
        int number,
        String city,
        String district,
        String state,
        String uf,
        String cep,
        String dateCreated,
        String dateUpdated
) {}
