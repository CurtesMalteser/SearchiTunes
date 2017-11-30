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

import com.curtesmalteser.searchitunes.JsonItunesParser;
import com.curtesmalteser.searchitunes.R;
import com.curtesmalteser.searchitunes.ItunesHTTPClient;
import com.curtesmalteser.searchitunes.adapter.ItemAdapter;
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

    private ListView listViewResults;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private class JSONItunesStuffTask extends AsyncTask<String, Void, ArrayList<ItunesStuff>> {

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
        protected ArrayList<ItunesStuff> doInBackground(String... params) {

            String search = editTextSearch.getText().toString();

            if (search.contains(" ")) {
                search = search.replaceAll(" ", "+");
            }

            ArrayList<ItunesStuff> itunesStuffArrayList = new ArrayList<>();

            ItunesHTTPClient itunesHTTPClient = new ItunesHTTPClient();

            String data = (itunesHTTPClient.getItunesStuffData(search));

            try {
                itunesStuffArrayList = JsonItunesParser.getItunesStuff(data);

                for (int i = 0; i < itunesStuffArrayList.size(); i++) {
                    String track = itunesStuffArrayList.get(i).getTrackName();
                    Log.d("AJDB", "getTrackName: " + track);
                }

            } catch (Throwable t) {
                t.printStackTrace();
            }

            return itunesStuffArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<ItunesStuff> itunesStuff) {
            super.onPostExecute(itunesStuff);

            List<ItunesStuff> iTunesData = itunesStuff;

            itemAdapter = new ItemAdapter(this.context, iTunesData);

            listViewResults.setAdapter(itemAdapter);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
