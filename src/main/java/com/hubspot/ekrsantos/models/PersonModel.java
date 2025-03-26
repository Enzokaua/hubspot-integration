package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonModel {
    @JsonProperty("email")
    private final String email;

    @JsonProperty("lastname")
    private final String lastname;

    @JsonProperty("firstname")
    private final String firstname;

    public PersonModel(String email, String lastname, String firstname) {
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
