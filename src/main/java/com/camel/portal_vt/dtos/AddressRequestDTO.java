package com.camel.portal_vt.dtos;

public record AddressRequestDTO(
        String userName,
        String addressType,
        AddressDetailsDTO addressDetails
) {}
