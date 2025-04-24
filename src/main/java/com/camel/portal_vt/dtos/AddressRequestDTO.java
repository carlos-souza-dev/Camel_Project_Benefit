package com.camel.portal_vt.dtos;

import com.camel.portal_vt.enums.AddressType;

public record AddressRequestDTO(
        String userName,
        AddressType addressType,
        AddressDetailsDTO addressDetails
) {}
