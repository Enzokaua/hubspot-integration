package com.hubspot.ekrsantos.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenModel {

    @JsonProperty("access_token")
    private final String acessToken;

    public String getAcessToken() {
        return acessToken;
    }

    public TokenModel (String acessToken){
        this.acessToken =  acessToken;}

}
