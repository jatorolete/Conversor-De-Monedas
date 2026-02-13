package com.jatorolete.conversor;

import com.google.gson.Gson;

public class EjemploGson {
    public static void main(String[] args) {

        // Creamos un objeto Persona (record simple)
        Persona persona = new Persona("Jorge", 37);

        // Instancia de Gson
        Gson gson = new Gson();

        // Serializamos el objeto a JSON
        String json = gson.toJson(persona);
        System.out.println("JSON generado: " + json);

        // Deserializamos el JSON a un objeto Persona
        Persona personaDeserializada = gson.fromJson(json, Persona.class);
        System.out.println("Nombre deserializado: " + personaDeserializada.nombre());
    }
}

// Record simple para la prueba
record Persona(String nombre, int edad) {}