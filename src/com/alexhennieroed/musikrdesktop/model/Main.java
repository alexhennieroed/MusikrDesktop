package com.alexhennieroed.musikrdesktop.model;

import com.alexhennieroed.musikrlib.managers.Director;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Controls the app
 * @author Alexander Hennie-Roed
 * @version 1.0.0
 */
public class Main extends Application {

    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        Director.getInstance().setMusicInterface(LocalMusicConnector.getInstance());
        mainStage.setTitle("Musikr Desktop");
        setScreen("LargePlayer");
        mainStage.show();
    }

    /**
     * Sets the screen of the stage to the selected one
     * @param screenName the name of the screen to be changed to
     */
    private void setScreen(String screenName) {
        //TODO
    }

}
