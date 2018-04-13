package model;

import model.data.ApiInformation;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class ApplicationModel {

    private final Path lifetimeStats = Paths.get("resources\\lifetime.txt");
    private final Path dailyStats = Paths.get("resources\\daily.txt");

    private int lifetimeWins;
    private int dailyWins;
    private ApiInformation api;

    public ApplicationModel() {
        api = new ApiInformation(this);
    }


    public boolean program() {
        boolean status = api.getWins();
        writeToFiles();
        return status;
    }


    public String getUsername() {
        return api.getUsername();
    }

    public int getLifetimeWins() {
        return lifetimeWins;
    }

    public int getDailyWins() {
        return dailyWins;
    }

    public void setStats(int tempLifeTimeWins) {
        if (lifetimeWins == 0) {
            lifetimeWins = tempLifeTimeWins;
            dailyWins = 0;
            writeToFiles();
        } else if (tempLifeTimeWins > lifetimeWins) {
            dailyWins += (tempLifeTimeWins - lifetimeWins);
            lifetimeWins = tempLifeTimeWins;
            writeToFiles();
        }
    }

    private void writeToFiles() {
        try (PrintWriter pwLifetime = new PrintWriter(Files.newBufferedWriter(lifetimeStats, CREATE, WRITE));
             PrintWriter pwDaily = new PrintWriter(Files.newBufferedWriter(dailyStats, CREATE, WRITE))) {
            pwLifetime.write(String.format("%s", lifetimeWins));
            pwDaily.write(String.format("%s", dailyWins));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
