package com.camel.portal_vt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ProcessingStatus {
    IN_PROGRESS("IN_PROGRESS"),
    CANCELLED("CANCELED"),
    COMPLETED("COMPLETED");

    public String value;
}
