package com.camel.portal_vt.dtos;

public record AddressDTO(
        String street,
        int number,
        String city,
        String district,
        String state,
        String uf,
        String cep,
        String fullAddress,
        String dateCreated,
        String dateUpdated
) {
    public AddressDTO formatAddress() {
        String formattedAddress = String.format(
                "%s, %d - %s, %s - %s, %s, Brasil",
                this.street,
                this.number,
                this.district,
                this.city,
                this.state,
                this.uf,
                this.cep
        );

        return new AddressDTO(
                this.street,
                this.number,
                this.city,
                this.district,
                this.state,
                this.uf,
                this.cep,
                formattedAddress,
                this.dateCreated,
                this.dateUpdated
        );
    }
}
