package com.camel.portal_vt.dtos;

public record AddressesDTO(
        AddressDTO homeAddress,
        AddressDTO workAddress
) {}
