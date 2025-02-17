package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.TransitDetails;

import java.math.BigDecimal;
import java.util.List;

public record SummaryRouteDTO(
        String distance,
        String duration,
        List<RouteDetailsDTO> routeDetails,
        String totalValue
) {
    public SummaryRouteDTO(String distance,
                           String duration,
                           List<RouteDetailsDTO> routeDetails,
                           String totalValue) {
        this.distance = distance;
        this.duration = duration;
        this.routeDetails = routeDetails;
        this.totalValue = totalValue;
    }

    ;
}
