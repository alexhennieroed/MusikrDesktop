package com.alexhennieroed.musikrdesktop.controller;

import com.alexhennieroed.musikrlib.managers.Director;
import com.alexhennieroed.musikrlib.model.Song;
import javafx.fxml.FXML;

/**
 * The controller for the large player setup
 * @author Alexander Hennie-Roed
 * @version 1.0.0
 */
public class LargePlayerController {

    @FXML
    public void selectSong(Song song) {
        Director.getInstance().getMusicManager().selectSong(song);
    }

    @FXML
    public void play() {
        Director.getInstance().getMusicManager().play();
    }

    @FXML
    public void pause() {
        Director.getInstance().getMusicManager().pause();
    }

    @FXML
    public void next() {
        Director.getInstance().getMusicManager().next();
    }

    @FXML
    public void prev() {
        Director.getInstance().getMusicManager().prev();
    }

    @FXML
    public void shuffleToggle() {
        Director.getInstance().getMusicManager().shuffleToggle();
    }

}
