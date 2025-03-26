package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AssociationModel {
    @JsonProperty("types")
    private final List<AssociationTypeModel> types;
    @JsonProperty("to")
    private final ToModel to;

    public AssociationModel(List<AssociationTypeModel> types, ToModel to) {
        this.types = types;
        this.to = to;
    }
}
