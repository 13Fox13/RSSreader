package com.klopov.andrey.rssreader;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.google.gson.Gson;
import com.klopov.andrey.rssreader.Adapter.FeedAdapter;
import com.klopov.andrey.rssreader.Common.HTTPDataHandler;
import com.klopov.andrey.rssreader.Model.RSSObject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS link
    final static String RSS_Link="http://feeds.feedburner.com/TechCrunch";
    //     final static String RSS_Link="https://lifehacker.com/rss";

    // and Json API from rss2json.com
    final static String RSS_to_Json_API="https://api.rss2json.com/v1/api.json?rss_url=";
    // final static String RSS_to_Json_API="https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("RSS");

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();

    }

    private void loadRSS() {
        @SuppressLint("StaticFieldLeak") AsyncTask<String,String,String> loadRSS = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();

            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject, getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_Link);
        loadRSS.execute(url_get_data.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_refresh){

            loadRSS();
        }

        return true;
    }
}
