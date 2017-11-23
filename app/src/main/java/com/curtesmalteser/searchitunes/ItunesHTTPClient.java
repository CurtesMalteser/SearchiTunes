package com.curtesmalteser.searchitunes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dias Bastião on 26-04-2017.
 */

public class ItunesHTTPClient {

    private static final String BASE_URL = "https://itunes.apple.com/search?term=";

    public String getItunesStuffData(String search) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {

            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL + search)).openConnection();
            //httpURLConnection = (HttpURLConnection) (new URL(BASE_URL + search + "&entity=podcast")).openConnection();
            //httpURLConnection = (HttpURLConnection) (new URL(BASE_URL + search + "&entity=musicVideo")).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            //it's commented out because the line bellow is used to post
            //data to the internet and we don't need it for this app
            //httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            //Now lets read the response (now we are connected to the internet)
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        return null;
    }

    public Bitmap getBitMapFromURL (String stringUrl) {
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
