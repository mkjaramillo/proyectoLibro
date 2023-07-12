package com.distribuida.service;

import com.distribuida.DTO.PersonaDTO;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Path("/persona")
public class ClientPersona {

    private final HttpClient httpClient=HttpClient.newHttpClient();
    private final Gson gson=new Gson();

    @GET
    @Path("/{id}")
    public PersonaDTO buscarPersona(@PathParam("id") Long  id) {
        try {

            URI uri = URI.create("http://localhost:8080/persona/" + id.toString());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Código de estado: " + statusCode);
            System.out.println("Respuesta: " + responseBody);

            if (statusCode == 200) {
                // Convertir la respuesta JSON a objeto PersonaDTO
                PersonaDTO personaDTO = gson.fromJson(responseBody, PersonaDTO.class);
                return personaDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
