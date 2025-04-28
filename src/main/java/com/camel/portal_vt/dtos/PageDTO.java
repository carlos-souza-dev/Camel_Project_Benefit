package com.camel.portal_vt.dtos;

import java.util.List;

public record PageDTO<T>(
        Integer total,
        Long totalItems,
        List<T> content,
        int number,
        Integer size
) {
}
