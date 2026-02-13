package com.jatorolete.conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {

    private final HttpClient cliente = HttpClient.newHttpClient();

    public String getRate(String base, String target, String apikey) {
        String url = "https://v6.exchangerate-api.com/v6/" + apikey + "/pair/" + base + "/" + target;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la solicitud HTTP: " + e.getMessage());
        }
    }
}
