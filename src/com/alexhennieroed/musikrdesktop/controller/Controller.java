package com.alexhennieroed.musikrdesktop.controller;

import com.alexhennieroed.musikrdesktop.model.Main;
import com.alexhennieroed.musikrlib.managers.Director;
import com.alexhennieroed.musikrlib.model.Album;
import com.alexhennieroed.musikrlib.model.Artist;
import com.alexhennieroed.musikrlib.model.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.io.IOException;

/**
 * A generic UI controller class to be extended
 * @author Alexander Hennie-Roed
 * @version 1.0.0
 */
public class Controller {

    protected Main mainApp;

    private boolean isPlaying;
    private Artist currentArtist;
    private Album currentAlbum;
    private Song currentSong;

    @FXML
    private ListView artistListView;
    @FXML
    private ListView albumListView;
    @FXML
    private ListView songListView;
    @FXML
    private Button playPauseButton;
    @FXML
    private ProgressBar playbackBar;

    @FXML
    private void initialize() {
        this.isPlaying = false;
        this.currentArtist = null;
        this.currentAlbum = null;
        this.currentSong = null;

        playPauseButton.setOnAction(event -> {
            if (isPlaying) {
                pause();
            } else {
                play();
            }
        });
    }

    /**
     * Creates an alert giving info about an error that occurred
     * @param e the exception that happened
     */
    private void errorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("There Has Been an Issue with the Song File");
        alert.setHeaderText("");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    /**
     * Selects the song
     * @param song the song to select
     */
    public void selectSong(Song song) {
        Director.getInstance().getMusicManager().selectSong(song);
    }

    /**
     * Plays the music
     */
    private void play() {
        try {
            Director.getInstance().getMusicManager().play();
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    /**
     * Pauses the music
     */
    private void pause() {
        Director.getInstance().getMusicManager().pause();
    }

    @FXML
    public void next() {
        try {
            Director.getInstance().getMusicManager().next();
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void prev() {
        try {
            Director.getInstance().getMusicManager().prev();
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void shuffleToggle() {
        Director.getInstance().getMusicManager().shuffleToggle();
    }

    @FXML
    public void closeProgram() { mainApp.close(); }

    public void setMainApp(Main app) { this.mainApp = app; }

}
