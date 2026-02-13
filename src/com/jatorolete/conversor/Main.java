package com.jatorolete.conversor;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        String apikey = "cab732d6fe750e47a2f9f6ea";
        System.out.println("Bienvenido al Conversor de Monedas");
        ExchangeRateClient cliente = new ExchangeRateClient();
        String json = cliente.getRate("USD","CLP", apikey);

        System.out.println("JSON recibido:");
        System.out.println(json);

        Gson gson = new Gson();
        ExchangeRatePair data = gson.fromJson(json, ExchangeRatePair.class);

        System.out.println("Tasa de conversión");
        System.out.println(data.conversion_rate());

    }
}
