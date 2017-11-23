package com.curtesmalteser.searchitunes;

import com.curtesmalteser.searchitunes.model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dias Bastião on 25-04-2017.
 */

public class JsonItunesParser {
/*
    //download the values from the web and stores them on the ItunesStuff.class
    public static ItunesStuff getItunesStuff (String url) throws JSONException {


        ItunesStuff itunesStuff = new ItunesStuff();

        JSONObject iTunesStuffJsonObject = new JSONObject(url);

        int length = iTunesStuffJsonObject.getInt("resultCount");
        itunesStuff.setResults(iTunesStuffJsonObject.getString("resultCount"));

        JSONArray resultsJsonArray = iTunesStuffJsonObject.getJSONArray("results");

        JSONObject artistObject = resultsJsonArray.getJSONObject(0);

        itunesStuff.setType(getString("wrapperType", artistObject));
        itunesStuff.setKind(getString("kind", artistObject));
        itunesStuff.setArtistName(getString("artistName", artistObject));
        if (artistObject.has("collectionName") && !artistObject.isNull("collectionName")) {
            itunesStuff.setCollectionName(getString("collectionName", artistObject));
        } else itunesStuff.setCollectionName("N/A");
        itunesStuff.setArtistViewURL(getString("artworkUrl100", artistObject));
        itunesStuff.setTrackName(getString("trackName", artistObject));

        return itunesStuff;

    }

    //this return all JSONObject to be parsed
    private static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject(tagName);
    }

    // these methods getString, getInt or geFloat are used to get strings, or ID's, or the prices of the products

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException {

        return jsonObject.getString(tagName);
    }

    private static int getInt(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(tagName);
    }

    private static float getFloat(String tagName, JSONObject jsonObject) throws JSONException {

        //the cast for float is because the double consumes more memory due to more precision digits
        return (float) jsonObject.getDouble(tagName);
    }

    //"isStreamable": true
    private static boolean getBollean(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getBoolean(tagName);
    }*/

}