package com.hubspot.ekrsantos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.ekrsantos.models.ContactModel;
import com.hubspot.ekrsantos.models.HubspotModel;
import com.hubspot.ekrsantos.models.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/hubspot")
public class HubspotController {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private final ObjectMapper mapper;
    private final HttpHeaders httpHeaders;
    private final Logger log;
    private HubspotModel hubspotModel;
    private TokenModel tokenModel;

    @Autowired
    public HubspotController(ObjectMapper objectMapper, HttpHeaders httpHeaders, Logger log) {
        this.mapper = objectMapper;
        this.httpHeaders = httpHeaders;
        this.log = log;
    }

    @PostMapping("/mappings")
    public Object createConfigs(@RequestBody HubspotModel hubspotModel) {
        log.info("createConfigs: do");
        this.hubspotModel = hubspotModel;
        log.info("createConfigs: done");
        return hubspotModel.toString();
    }

    @GetMapping("/authorize")
    public RedirectView authorizeUrlHubspot() {
        log.info("authorizeUrlHubspot: inicialize...");
        return new RedirectView(UriComponentsBuilder.fromUriString(hubspotModel.getUrlConnection() + hubspotModel.getAppId() + hubspotModel.getEndpointAuthorize())
                .queryParam("client_id", hubspotModel.getClientId())
                .queryParam("redirect_uri", hubspotModel.getRedirectUrl())
                .queryParam("scope", hubspotModel.getScopes())
                .queryParam("state", hubspotModel.getState())
                .build()
                .toUriString());
    }

    @GetMapping("/callback")
    public String processCallback(@RequestParam String code) {
        try {
            log.info("processCallback: do");
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("client_id", hubspotModel.getClientId());
            body.add("client_secret", hubspotModel.getClientSecret());
            body.add("redirect_uri", hubspotModel.getRedirectUrl());
            body.add("code", code);
            log.info(body.toString());
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<String> response = REST_TEMPLATE.postForEntity(hubspotModel.getUrlOauthToken(), request, String.class);
            tokenModel = mapper.readValue(response.getBody(), TokenModel.class);
            log.info("processCallback: done");
            return "Authentication OK.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/contacts")
    public Object createContact(@RequestBody ContactModel contactModel) {
        try {
            log.info("createContact: do");
            log.info(contactModel.toString());

            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setBearerAuth(tokenModel.getAcessToken());
            HttpEntity<ContactModel> request = new HttpEntity<>(contactModel, httpHeaders);
            ResponseEntity<String> response = REST_TEMPLATE.postForEntity(hubspotModel.getUlrHubapi() + hubspotModel.getEndpointContacts(), request, String.class);

            if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(403))) {
                log.info("createContact: Authorization invalid!");
                return authorizeUrlHubspot();
            }

            log.info("createContact: done");
            return response.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/webhook")
    public void webhookContent(@RequestBody String payload) {
        log.info("webhookContent: do");
        log.info(payload);
        log.info("webhookContent: done");
    }

}