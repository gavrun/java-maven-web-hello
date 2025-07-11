package com.example.countrylookup;

//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.http.HttpServlet;
import javax.json.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;

import java.io.*;
import java.net.http.*;
import java.net.URI;

public class CountryLookupServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String query = req.getParameter("country");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.com/v3.1/name/" + query))
                .build();

        String result = "";

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonReader reader = Json.createReader(new StringReader(response.body()));
            JsonArray countries = reader.readArray();

            StringBuilder output = new StringBuilder();
            for (JsonValue value : countries) {
                JsonObject obj = value.asJsonObject();
                String name = obj.getJsonObject("name").getString("common", "N/A");
                output.append("<li>").append(name).append("</li>");
            }

            result = "<ul>" + output + "</ul>";
        } catch (Exception e) {
            result = "<p>Error fetching country data.</p>";
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
