package com.curtesmalteser.searchitunes.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.curtesmalteser.searchitunes.R;
import com.curtesmalteser.searchitunes.ItunesHTTPClient;
import com.curtesmalteser.searchitunes.JsonItunesParser;
import com.curtesmalteser.searchitunes.adapter.ItemAdapter;
import com.curtesmalteser.searchitunes.model.ITunesData;
import com.curtesmalteser.searchitunes.model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResults;
    private TextView txtType;
    private TextView txtArtistName;
    private TextView txtCollectionName;
    private TextView txtKind;
    private TextView txtTrackName;

    private EditText editTextSearch;

    private ImageView imgArt;

    private Button btnGetData;

    private String imgURL;
    private String imgURLtest;

    private Bitmap bitmap;
    private Bitmap bitmapTest;

    private ListView listViewResults;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResults = (TextView) findViewById(R.id.txt_results);
        txtType = (TextView) findViewById(R.id.txtType);
        txtArtistName = (TextView) findViewById(R.id.txtArtistName);
        txtCollectionName = (TextView) findViewById(R.id.txtCollectionName);
        txtKind = (TextView) findViewById(R.id.txtKind);
        txtTrackName = (TextView) findViewById(R.id.txtTrackName);
        imgArt = (ImageView) findViewById(R.id.imgArt);
        btnGetData = (Button) findViewById(R.id.btnGetData);

        editTextSearch = (EditText) findViewById(R.id.et_search);

        btnGetData.setOnClickListener(MainActivity.this);

        listViewResults = (ListView) findViewById(R.id.list_search_results);

    }

    @Override
    public void onClick(View view) {

        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();
    }

    private class JSONItunesStuffTask extends AsyncTask<String, Void, ArrayList<ITunesData>> {

        Context context;
        ProgressDialog progressDialog;

        public JSONItunesStuffTask(Context context) {

            this.context = context;
            progressDialog = new ProgressDialog(context);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Downloading Info from iTunes... Please Wait");
            progressDialog.show();

        }

        @Override
        protected ArrayList<ITunesData> doInBackground(String... params) {

            ArrayList<ITunesData> dataArryaList = new ArrayList();

            String search = editTextSearch.getText().toString();

            if (search.contains(" ")) {
                search = search.replaceAll(" ", "+");
            }

            ItunesStuff itunesStuff = new ItunesStuff();

            ItunesHTTPClient itunesHTTPClient = new ItunesHTTPClient();

            String data = (itunesHTTPClient.getItunesStuffData(search));


            try {
                JSONObject iTunesStuffJsonObject = new JSONObject(data);
               // int length = iTunesStuffJsonObject.getInt("resultCount");
                //itunesStuff.setResults(iTunesStuffJsonObject.getString("resultCount"));

                JSONArray resultsJsonArray = iTunesStuffJsonObject.getJSONArray("results");
                for (int i = 0; i < resultsJsonArray.length(); i++) {
                    JSONObject artistObject = resultsJsonArray.getJSONObject(i);

                    //itunesStuff.setType(getString("wrapperType", artistObject));
                    String wrapperType = getString("wrapperType", artistObject);

                    //itunesStuff.setKind(getString("kind", artistObject));
                    String kind = getString("kind", artistObject);

                    //itunesStuff.setArtistName(getString("artistName", artistObject));
                    String artistName = getString("artistName", artistObject);

                    String collectionName = "test to try";

                    if (artistObject.has("collectionName") && !artistObject.isNull("collectionName")) {
                       // itunesStuff.setCollectionName(getString("collectionName", artistObject));
                        collectionName = getString("collectionName", artistObject);

                    } else itunesStuff.setCollectionName("N/A");

                    //itunesStuff.setArtistViewURL(getString("artworkUrl100", artistObject));
                    String artworkUrl100 = getString("artworkUrl100", artistObject);

                    try {
                        imgURLtest = artworkUrl100;
                        bitmapTest = (itunesHTTPClient.getBitMapFromURL(imgURLtest));

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }

                   // itunesStuff.setTrackName(getString("trackName", artistObject));
                    String trackName = getString("trackName", artistObject);

                    dataArryaList.add(new ITunesData(i, wrapperType,
                            kind,
                            artistName,
                            collectionName,
                            bitmapTest,
                            trackName));
                }

                /*try {
                    imgURL = itunesStuff.getArtistViewURL();
                    bitmap = (itunesHTTPClient.getBitMapFromURL(imgURL));

                } catch (Throwable t) {
                    t.printStackTrace();
                }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ;


            return dataArryaList;
        }

        @Override
        protected void onPostExecute(ArrayList<ITunesData> itunesStuff) {
            super.onPostExecute(itunesStuff);

            List<ITunesData> iTunesData = itunesStuff;

            txtResults.setText(String.valueOf(iTunesData.size()));
            txtArtistName.setText(iTunesData.get(0).getArtistName());
            txtType.setText(iTunesData.get(0).getWrapperType());
            txtKind.setText(iTunesData.get(0).getKind());
            txtCollectionName.setText(iTunesData.get(0).getCollectionName());
            txtTrackName.setText(iTunesData.get(0).getTrackName());
            imgArt.setImageBitmap(iTunesData.get(0).getArtworkUrl100());

            itemAdapter = new ItemAdapter(this.context, iTunesData);

            listViewResults.setAdapter(itemAdapter);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException {

        return jsonObject.getString(tagName);
    }

}
