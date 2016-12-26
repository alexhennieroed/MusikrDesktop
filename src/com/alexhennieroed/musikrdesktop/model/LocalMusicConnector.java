package com.alexhennieroed.musikrdesktop.model;

import com.alexhennieroed.musikrlib.interfaces.MusicInterface;
import com.alexhennieroed.musikrlib.managers.Director;
import com.alexhennieroed.musikrlib.model.Album;
import com.alexhennieroed.musikrlib.model.Artist;
import com.alexhennieroed.musikrlib.model.Song;

import java.io.File;
import java.util.ArrayList;
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
    private File musDirFile;

    /**
     * Private constructor of the connector singleton
     */
    private LocalMusicConnector() {
        this.musicDirectory = Director.getInstance().getSettings().getMusicDirectory();
        this.musDirFile = new File(musicDirectory);
    }

    @Override
    public Song getSong(Song song) {
        return new Song("Default", new Artist("Default"), new Album("Default", new Artist("Default")));
    }

    @Override
    public List<Artist> getArtists() {
        try {
            List<Artist> artists = new ArrayList<>();
            File[] artistFiles = loopArtists();
            return artists;
        } catch (NullPointerException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Album> getAlbums() {
        try {
            List<Album> albums = new ArrayList<>();
            File[] albumFiles = loopAlbums(loopArtists());
            return albums;
        } catch (NullPointerException e) {
        System.out.println(e.toString());
        return new ArrayList<>();
        }
    }

    @Override
    public List<Song> getSongs() {
        try {
            List<Song> songs = new ArrayList<>();
            File[] songFiles = loopSongs(loopAlbums(loopArtists()));
            return songs;
        } catch (NullPointerException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    /**
     * Loops through the artist files based on the musicDirectory
     * @return the list of artist files
     * @throws NullPointerException if listFiles() throws one
     */
    private File[] loopArtists() throws NullPointerException {
        List<File> artistFiles = new ArrayList<>();
        for (File f : musDirFile.listFiles()) {
            if (f.isDirectory()) {
                artistFiles.add(f);
            }
        }
        return (File[]) artistFiles.toArray();
    }

    /**
     * Loops through the album files based  on the list of artists given
     * @param artists the list of artist files
     * @return the list of album files
     * @throws NullPointerException if listFiles() throws one
     */
    private File[] loopAlbums(File[] artists) throws NullPointerException {
        List<File> albumFiles = new ArrayList<>();
        for (File f : artists) {
            for (File x : f.listFiles()) {
                if (f.isDirectory()) {
                    albumFiles.add(x);
                }
            }
        }
        return (File[]) albumFiles.toArray();
    }

    /**
     * Loops through song files based on the list of album files given
     * @param albums the list of album files
     * @return the list of song files
     * @throws NullPointerException if listFiles() throws one
     */
    private File[] loopSongs(File[] albums) throws NullPointerException {
        List<File> songFiles = new ArrayList<>();
        for (File f : albums) {
            for (File x : f.listFiles()) {
                if (!f.isDirectory()) {
                    songFiles.add(x);
                }
            }
        }
        return (File[]) songFiles.toArray();
    }

    /**
     * Gets the instance of the connector
     * @return the instance
     */
    public static LocalMusicConnector getInstance() { return localConnector; }

}
