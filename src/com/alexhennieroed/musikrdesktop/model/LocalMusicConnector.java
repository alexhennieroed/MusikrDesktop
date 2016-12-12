package com.alexhennieroed.musikrdesktop.model;

import com.alexhennieroed.musikrlib.interfaces.MusicInterface;
import com.alexhennieroed.musikrlib.managers.Director;
import com.alexhennieroed.musikrlib.model.Album;
import com.alexhennieroed.musikrlib.model.Artist;
import com.alexhennieroed.musikrlib.model.Song;

import java.util.List;

/**
 * Connects to music on the local disk
 * Singleton to prevent multiple instances
 *
 * @author Alexander Hennie-Roed
 * @version 1.0.0
 */
public class LocalMusicConnector implements MusicInterface {

    private static LocalMusicConnector localConnector = new LocalMusicConnector();

    private String musicDirectory;

    /**
     * Private constructor of the connector singleton
     */
    private LocalMusicConnector() {
        this.musicDirectory = Director.getSettings().getMusicDirectory();
    }

    @Override
    public Song getSong(Song song) {
        return null;
    }

    @Override
    public List<Artist> getArtists() {
        return null;
    }

    @Override
    public List<Album> getAlbums() {
        return null;
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }

    /**
     * Gets the instance of the connector
     * @return the instance
     */
    public static LocalMusicConnector getInstance() { return localConnector; }

}
