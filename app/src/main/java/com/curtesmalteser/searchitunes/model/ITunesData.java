package com.curtesmalteser.searchitunes.model;

import android.graphics.Bitmap;

/**
 * Created by anton on 23/11/2017.
 */

public class ITunesData {

    private int id;
    private String wrapperType;
    private String kind;
    private String artistName;
    private String collectionName;
    private Bitmap artworkUrl100;
    private String trackName;

    public ITunesData(int id, String wrapperType, String kind, String artistName, String collectionName, Bitmap artworkUrl100, String trackName) {
        this.id = id;
        this.wrapperType = wrapperType;
        this.kind = kind;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.artworkUrl100 = artworkUrl100;
        this.trackName = trackName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Bitmap getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(Bitmap artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}
