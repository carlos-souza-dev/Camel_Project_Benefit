package com.camel.portal_vt.dtos.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Leg(
        Distance distance,
        Duration duration,
        List<Step> steps
) {
    public List<TransitDetails> summaryRoute(){

        if (steps != null) {
            List<TransitDetails> transitDetails = steps.stream()
                    .filter(datail -> datail.transit_details() != null)
                    .map(Step::transit_details)
                    .toList();

            return transitDetails;
        }

        return List.of();
    }
}
