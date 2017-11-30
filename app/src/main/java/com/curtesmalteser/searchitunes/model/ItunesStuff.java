package com.curtesmalteser.searchitunes.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Dias Bastião on 25-04-2017.
 */

public class ItunesStuff {

    private int id;
    private String type;
    private String kind;
    private String artistName;
    private String collectionName;
    private String trackName;
    private Bitmap artistViewURL;

    public ItunesStuff(){}

    public ItunesStuff(int id,String type, String kind, String artistName, String collectionName, String trackName, Bitmap artistViewURL) {
        this.id = id;
        this.type = type;
        this.kind = kind;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.artistViewURL = artistViewURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Bitmap getArtistViewURL() {
        return artistViewURL;
    }

    public void setArtistViewURL(Bitmap artistViewURL) {
        this.artistViewURL = artistViewURL;
    }

}