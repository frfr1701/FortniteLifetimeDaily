package model.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.ApplicationModel;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiInformation {

    private String username;
    private ApplicationModel model;

    public ApiInformation(ApplicationModel model) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("resources\\username.txt"))) {
            username = br.readLine().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.model = model;
    }

    public boolean getWins(){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.fortnitetracker.com/v1/profile/pc/" + username);
            request.addHeader("TRN-Api-Key", "a5a2a9c8-1384-4f42-8065-577057593852");
            HttpResponse response = client.execute(request);
            JsonElement jsonElement = (new JsonParser()).parse(new InputStreamReader(response.getEntity().getContent()));
            JsonObject userData = jsonElement.getAsJsonObject();

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (userData.has("error")) {
                    return false;
                }
                int tempLifeTimeWins = Integer.valueOf(userData.getAsJsonArray("lifeTimeStats").get(8).getAsJsonObject()
                        .get("value").toString().replace("\"", "").trim());
                model.setStats(tempLifeTimeWins);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    public String getUsername() {
        return username;
    }
}
