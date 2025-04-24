package com.camel.portal_vt.dtos;

import com.camel.portal_vt.enums.ProcessingStatus;

public record ProcessingHistDTO(
        String userName,
        String date,
        String time,
        String event,
        ProcessingStatus status
) {
}
