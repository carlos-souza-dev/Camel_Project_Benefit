package com.camel.portal_vt.dtos;

public record AddressRequestDTO(
        String userName,
        AddressDetailsDTO address
) {}
