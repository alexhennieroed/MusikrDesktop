package com.alexhennieroed.musikrdesktop.model;

import com.alexhennieroed.musikrlib.managers.Director;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
        try {
            mainStage = stage;
            if (Director.getInstance().getSettings().getMusicDirectory() == null) {
                DirectoryChooser dc = new DirectoryChooser();
                File selectedDirectory = dc.showDialog(mainStage);
                if (selectedDirectory != null) {
                    Director.getInstance().getSettings().setMusicDirectory(
                            selectedDirectory.getAbsolutePath());
                }
            }
            Director.getInstance().setMusicInterface(LocalMusicConnector.getInstance());
            mainStage.setTitle("Musikr Desktop");
            setScreen("LargePlayer");
            mainStage.show();
        } catch (IOException e) {
            System.out.println("Error writing to the settings file");
            e.printStackTrace();
        }
    }

    /**
     * Sets the screen of the stage to the selected one
     * @param screenName the name of the screen to be changed to
     */
    private void setScreen(String screenName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("com/alexhennieroed/musikrdesktop/view/"
                    + screenName + ".fxml"));
            mainStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            System.out.println("Issues with loading file " + screenName + ".fxml");
            e.printStackTrace();
        }
    }

    /**
     * Closes the program
     */
    public void close() { mainStage.close(); }

}
