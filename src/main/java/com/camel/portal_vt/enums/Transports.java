package com.camel.portal_vt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum Transports {

    BUS("BUS"),
    SUBWAY("SUBWAY");

    private String value;
}
