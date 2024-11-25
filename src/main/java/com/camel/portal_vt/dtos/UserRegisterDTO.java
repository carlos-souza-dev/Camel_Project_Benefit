package com.camel.portal_vt.dtos;

public record UserRegisterDTO (
        String name,
        String userName,
        String password,
        Address homeAddress,
        Address jobAddress
) {}
