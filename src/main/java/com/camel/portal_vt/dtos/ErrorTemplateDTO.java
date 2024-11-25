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
public class ErrorTemplateDTO {

    private Integer _erroCode;
    private String _message;
    private String _httpStatus;
    private String _datails;
}
