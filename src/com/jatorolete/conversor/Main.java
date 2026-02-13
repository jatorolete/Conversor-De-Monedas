package com.jatorolete.conversor;

import com.google.gson.Gson;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String apikey = "cab732d6fe750e47a2f9f6ea";

        System.out.println("Bienvenido al Conversor de Monedas");

        int opcion = 0;

        while (opcion != 7){
            System.out.println("*******************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("*******************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("*******************************");
            System.out.print("Elija una opción válida: ");

            opcion = scanner.nextInt();

            if (opcion == 7){
                System.out.println("Gracias por usar el conversor de Monedas. ¡Hasta luego!");
                break;
            }
            String base = "";
            String target = "";

            switch (opcion){
                case 1 -> { base = "USD"; target = "ARS"; }
                case 2 -> { base = "ARS"; target = "USD"; }
                case 3 -> { base = "USD"; target = "BRL"; }
                case 4 -> { base = "BRL"; target = "USD"; }
                case 5 -> { base = "USD"; target = "COP"; }
                case 6 -> { base = "COP"; target = "USD"; }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            //solicitamos el monto
            System.out.println("Ingrese el monto a convertir: ");
            double monto = scanner.nextDouble();
            //obtiene la tasa de conversión
            ExchangeRateClient cliente = new ExchangeRateClient();
            String json = cliente.getRate(base, target, apikey);

            //convierte el JSON a nuestro record
            Gson gson = new Gson();
            ExchangeRatePair data = gson.fromJson(json, ExchangeRatePair.class);

            double tasa = data.conversion_rate();

            //activa la clase conversor
            Conversor conversor = new Conversor();
            double resultado = conversor.convertir(monto, tasa);

            System.out.println("Tasa de conversión" + tasa);
            System.out.println("Resultado final: " + resultado + " " + target);
            System.out.println();
        }
    }
}
