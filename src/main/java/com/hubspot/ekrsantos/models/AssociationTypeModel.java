package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociationTypeModel {
    @JsonProperty("associationCategory")
    private final String associationCategory;

    @JsonProperty("associationTypeId")
    private final int associationTypeId;

    public AssociationTypeModel(String associationCategory, int associationTypeId) {
        this.associationCategory = associationCategory;
        this.associationTypeId = associationTypeId;
    }
}
