package com.camel.portal_vt.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  ReturnStatusDTO {

    private Integer code;
    private String description;
    private HttpStatus httpStatus;
}
