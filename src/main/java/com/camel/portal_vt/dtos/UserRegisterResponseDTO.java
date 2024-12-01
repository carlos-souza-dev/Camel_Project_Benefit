package com.camel.portal_vt.dtos;

public record UserRegisterResponseDTO (
        long userId,
        String name,
        String userName,
        String password,
        Address homeAddress,
        Address workAddress
){
}
