package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToModel {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String id;

    public ToModel(String id) {
        this.id = id;
    }
}
