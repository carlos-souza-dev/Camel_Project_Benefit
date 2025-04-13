package com.camel.portal_vt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum AddressType {
    RESIDENTIAL("RESIDENTIAL"),
    BUSINESS("BUSINESS");

    private String value;
}
