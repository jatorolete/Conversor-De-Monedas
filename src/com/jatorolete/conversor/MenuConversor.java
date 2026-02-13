package com.jatorolete.conversor;

import com.google.gson.Gson;
import java.util.Scanner;

public class MenuConversor {

    private final Scanner scanner = new Scanner(System.in);
    private final String apikey = "cab732d6fe750e47a2f9f6ea";

    public void mostrarMenu() {

        int opcion = 0;

        while (opcion != 7){
            System.out.println("*******************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("*******************************");
            System.out.println("1) Dólar (USD) => Peso argentino (ARS)");
            System.out.println("2) Peso argentino (ARS) => Dólar (USD)");
            System.out.println("3) Dólar (USD) => Real brasileño (BRL)");
            System.out.println("4) Real brasileño (BRL) => Dólar (USD)");
            System.out.println("5) Dólar (USD) => Peso colombiano (COP)");
            System.out.println("6) Peso colombiano (COP) => Dólar (USD)");
            System.out.println("7) Dólar (USD) => Peso chileno (CLP)");
            System.out.println("8) Peso chileno (CLP) => Dólar (USD)");
            System.out.println("9) Peso chileno (CLP) => Peso argentino (ARS)");
            System.out.println("10) Peso argentino (ARS) => Peso chileno (CLP)");
            System.out.println("11) Peso chileno (CLP) => Sol peruano (PEN)");
            System.out.println("12) Peso chileno (CLP) => Real brasileño (BRL)");
            System.out.println("13) Salir");
            System.out.println("*******************************");
            System.out.print("Elija una opción válida: ");


            opcion = scanner.nextInt();

            if (opcion == 7){
                System.out.println("Gracias por usar el conversor de Monedas. ¡Hasta luego!");
                break;
            }
            String base = "";
            String target = "";

            switch (opcion) {
                case 1 -> { base = "USD"; target = "ARS"; }
                case 2 -> { base = "ARS"; target = "USD"; }
                case 3 -> { base = "USD"; target = "BRL"; }
                case 4 -> { base = "BRL"; target = "USD"; }
                case 5 -> { base = "USD"; target = "COP"; }
                case 6 -> { base = "COP"; target = "USD"; }
                case 7 -> { base = "USD"; target = "CLP"; }
                case 8 -> { base = "CLP"; target = "USD"; }
                case 9 -> { base = "CLP"; target = "ARS"; }
                case 10 -> { base = "ARS"; target = "CLP"; }
                case 11 -> { base = "CLP"; target = "PEN"; }
                case 12 -> { base = "CLP"; target = "BRL"; }
                case 13 -> {
                    System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
                    return;
                }
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

            System.out.println("Tasa de conversión: " + tasa);
            System.out.println("Resultado final: " + resultado + " " + target);
            System.out.println();
        }
    }
}
