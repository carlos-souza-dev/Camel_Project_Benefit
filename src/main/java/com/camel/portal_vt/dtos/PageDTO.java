package com.camel.portal_vt.dtos;

import java.util.List;

public record PageDTO<T>(
        Integer totalPage,
        Long totalItems,
        List<T> content,
        int pageNumber,
        Integer pageSize
) {
}
