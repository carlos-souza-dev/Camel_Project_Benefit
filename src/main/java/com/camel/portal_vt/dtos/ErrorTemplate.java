package com.camel.portal_vt.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorTemplate {

    @JsonProperty("_erroCode")
    private Integer _erroCode = null;

    @JsonProperty("_message")
    private String _message = null;

    @JsonProperty("_httpStatus")
    private String _httpStatus = null;

    @JsonProperty("_datails")
    private String _datails = null;
}
