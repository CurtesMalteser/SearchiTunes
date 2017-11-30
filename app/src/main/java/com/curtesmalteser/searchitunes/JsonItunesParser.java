package com.curtesmalteser.searchitunes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.curtesmalteser.searchitunes.model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dias Bastião on 25-04-2017.
 */

public class JsonItunesParser {

    //download the values from the web and stores them on the ItunesStuff.class
    public static ArrayList<ItunesStuff> getItunesStuff (String url) throws JSONException {



        JSONObject iTunesStuffJsonObject = new JSONObject(url);

        JSONArray resultsJsonArray = iTunesStuffJsonObject.getJSONArray("results");
        resultsJsonArray.length();

        ArrayList<ItunesStuff> itunesStuffArrayList = new ArrayList<>();

        for (int i = 0; i < resultsJsonArray.length(); i++) {
            JSONObject artistObject = resultsJsonArray.getJSONObject(i);

            Bitmap bitmap = getBitMapFromURL(getString("artworkUrl100", artistObject));

            itunesStuffArrayList.add(i, new ItunesStuff(
                    i,
                    getString("wrapperType", artistObject),
                    getString("kind", artistObject),
                    getString("artistName", artistObject),
                    getString("collectionName", artistObject),
                    getString("trackName", artistObject),
                    bitmap

            ));
        }

        /*for (int y = 0; y < itunesStuffArrayList.size(); y++) {
            Log.d("AJDB", "getItunesStuff: " + itunesStuffArrayList.get(y).getTrackName());
        }*/

        //return itunesStuff;
        return itunesStuffArrayList;
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
    }

    public static Bitmap getBitMapFromURL(String stringUrl) {
        Bitmap bitmap = null;

        try {
            URL url = new URL(stringUrl);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bitmap;
    }

}