package com.hubspot.ekrsantos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HubspotModel {

    @JsonProperty("clientId")
    private final String clientId;

    @JsonProperty("redirectUri")
    private final String redirectUrl;

    @JsonProperty("scopes")
    private final String scopes;

    @JsonProperty("state")
    private final String state;

    @JsonProperty("oauthUrlConnection")
    private final String urlConnection;

    @JsonProperty("clientSecret")
    private final String clientSecret;

    @JsonProperty("urlOauthv1")
    private final String urlOauthToken;

    @JsonProperty("appId")
    private final String appId;

    @JsonProperty("authorizeEndpoint")
    private final String endpointAuthorize;

    @JsonProperty("urlHubApi")
    private final String ulrHubapi;

    @JsonProperty("contactsEndpoint")
    private final String endpointContacts;


    public HubspotModel(String clientId,
                        String redirectUrlString,
                        String scopes,
                        String state,
                        String urlConnection,
                        String clientSecret,
                        String urlOauthToken,
                        String appId,
                        String endpointAuthorize,
                        String urlHubapi,
                        String endpointContacts) {

        this.clientId = clientId;
        this.redirectUrl = redirectUrlString;
        this.scopes = scopes;
        this.state = state;
        this.urlConnection = urlConnection;
        this.clientSecret = clientSecret;
        this.urlOauthToken = urlOauthToken;
        this.appId = appId;
        this.endpointAuthorize = endpointAuthorize;
        this.ulrHubapi = urlHubapi;
        this.endpointContacts = endpointContacts;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getScopes() {
        return scopes;
    }

    public String getState() {
        return state;
    }

    public String getUrlConnection() {
        return urlConnection;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUrlOauthToken() {
        return urlOauthToken;
    }

    public String getEndpointAuthorize() {
        return endpointAuthorize;
    }

    public String getAppId() {
        return appId;
    }

    public String getUlrHubapi() {
        return ulrHubapi;
    }

    public String getEndpointContacts() {
        return endpointContacts;
    }

}
