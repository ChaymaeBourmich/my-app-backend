package com.example.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class PowerBIController {
    private String getAccessToken() throws IOException, InterruptedException {
        String tenantId = "TON_TENANT_ID";
        String clientId = "TON_CLIENT_ID";
        String clientSecret = "TON_CLIENT_SECRET";
        String scope = "https://analysis.windows.net/powerbi/api/.default";
        String tokenEndpoint = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";

        String body = "grant_type=client_credentials" +
                "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8) +
                "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenEndpoint))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Extraire access_token
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());
            return json.get("access_token").asText();
        } else {
            throw new RuntimeException("Erreur d'obtention du token : " + response.body());
        }
    }



    @PostMapping("/api/refresh-dataset")
    public ResponseEntity<String> refreshDataset() {
        try {
            String accessToken = getAccessToken(); // OAuth2 token
            String groupId = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
            String datasetId = "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.powerbi.com/v1.0/myorg/groups/" + groupId + "/datasets/" + datasetId + "/refreshes"))
                    .header("Authorization", "Bearer " + accessToken)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.status(response.statusCode()).body(response.body());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur: " + e.getMessage());
        }
    }


}
