/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author derri
 */
public class MailValidator {

    private static final String API_KEY = "1bc34d69691f9cee41b4b79292b37a0f";
    
    public static boolean validarCorreo(String email) {
        try {
            String encodedEmail = URLEncoder.encode(email, "UTF-8");
            String apiUrl = String.format(
                "http://apilayer.net/api/check?access_key=%s&email=%s&smtp=1",
                API_KEY, encodedEmail
            );

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Leer respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder jsonResult = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                jsonResult.append(inputLine);
            }
            in.close();

            System.out.println("Respuesta JSON cruda: " + jsonResult);

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonResult.toString(), JsonObject.class);

        if (json.has("success") && json.get("success").isJsonPrimitive() && !json.get("success").getAsBoolean()) {
            System.out.println("¡Error desde la API! Probablemente clave incorrecta o límite alcanzado.");
            return false;
        }

        boolean smtp = json.has("smtp_check") && !json.get("smtp_check").isJsonNull()
                       ? json.get("smtp_check").getAsBoolean()
                       : false;

        boolean formatValid = json.has("format_valid") && !json.get("format_valid").isJsonNull()
                       ? json.get("format_valid").getAsBoolean()
                       : false;

        boolean disposable = json.has("disposable") && !json.get("disposable").isJsonNull()
                       ? json.get("disposable").getAsBoolean()
                       : false;

        System.out.println("SMTP válido: " + smtp);
        System.out.println("Formato válido: " + formatValid);
        System.out.println("Es desechable: " + disposable);
        
        return disposable;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean correoExiste(String email) {
        try {
            String encodedEmail = URLEncoder.encode(email, "UTF-8");
            String apiUrl = String.format(
                "http://apilayer.net/api/check?access_key=%s&email=%s",  // OJO: http, no https en plan gratuito
                API_KEY, encodedEmail
            );

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder jsonResult = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonResult.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(jsonResult.toString(), JsonObject.class);

            // Manejar errores
            if (json.has("success") && !json.get("success").getAsBoolean()) {
                System.err.println("Error API: " + json.get("error").getAsJsonObject().get("type").getAsString());
                return false;
            }

            boolean formatValid = json.has("format_valid") && json.get("format_valid").getAsBoolean();
            boolean mxFound = json.has("mx_found") && json.get("mx_found").getAsBoolean();
            boolean disposable = json.has("disposable") && json.get("disposable").getAsBoolean();
            boolean smtpCheck = json.has("smtp_check") && !json.get("smtp_check").isJsonNull()
                                && json.get("smtp_check").getAsBoolean();

            String domain = json.has("domain") ? json.get("domain").getAsString().toLowerCase() : "";

            // Dominios donde smtp_check no es fiable
            boolean smtpIrrelevante = domain.equals("gmail.com") ||
                                      domain.equals("hotmail.com") ||
                                      domain.equals("outlook.com") ||
                                      domain.equals("yahoo.com") ||
                                      domain.equals("icloud.com");

            return formatValid && mxFound && !disposable && (smtpCheck || smtpIrrelevante);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }   

    
    
}
