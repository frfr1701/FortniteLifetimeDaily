package controller;

import model.ApplicationModel;
import view.ApplicationView;

public class ApplicationController {

    ApplicationModel model;
    ApplicationView view;

    public ApplicationController() {
        model = new ApplicationModel();
        view = new ApplicationView();
    }

    public void start(){
        while(true) {
            boolean status = model.program();
            if (status) {
                try {
                    view.setL("User: " + model.getUsername());
                    view.setL2("Daily wins: " + model.getDailyWins());
                    view.setL3("Lifetime wins: " + model.getLifetimeWins());
                    for (int i = 5*60; i >= -1; i--) {
                        Thread.sleep(1000);
                        String minutes = String.valueOf(i / 60);
                        String seconds = String.valueOf(i % 60);
                        if (2 > seconds.length()) {
                            seconds = "0" + seconds;
                        }
                        if (2 > minutes.length()) {
                            minutes = "0" + minutes;
                        }
                        if (i == -1) {
                            view.setL4("Next update: Loading data...");
                        } else {
                            view.setL4("Next update: " + minutes + ":" + seconds + " minutes");
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                view.setL("User: Doesn't exist");
                view.setL2("Daily wins: No user found");
                view.setL3("Lifetime wins: No user found");
                view.setL4("Next update: No user found");
            }
        }
    }
}
