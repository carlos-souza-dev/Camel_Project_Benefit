package com.camel.portal_vt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Destiny {

    HOME("GO-HOME"),
    WORK("GO-WORK");

    private String value;
}
