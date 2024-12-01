package com.camel.portal_vt.dtos;

import java.util.Date;

public record UserDTO (
        String userId,
        String name,
        String userName,
        String password,
        String dateCreated,
        String dateUpdated){
}
