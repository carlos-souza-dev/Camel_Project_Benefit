package com.camel.portal_vt.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {

    String name;

    String userName;

    String password;

    Address homeAddress;

    Address workAddress;
}
