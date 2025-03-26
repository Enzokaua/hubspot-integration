package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ContactModel {
    @JsonProperty("associations")
    private final List<AssociationModel> associations;

    @JsonProperty("objectWriteTraceId")
    private final String objectWriteTraceId;

    @JsonProperty("properties")
    private final PersonModel properties;

    public ContactModel(List<AssociationModel> associations, String objectWriteTraceId, PersonModel properties) {
        this.associations = associations;
        this.objectWriteTraceId = objectWriteTraceId;
        this.properties = properties;
    }

}
