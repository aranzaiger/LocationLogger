package com.ex2.sagid.aranz.ex2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, GoogleApiClient.ConnectionCallbacks  {
    protected GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Ex2";
    private final int SAMPLE_RATE = 5000, SAMPLE_DELAY = 1000;
    protected EditText lat_et, lon_et;
    protected Button search_btn;
    protected TextView address_tv;
    protected ListView location_lv;
    protected SimpleCursorAdapter location_adapter;
    protected View location_item;
    protected Cursor locations;
    protected DBHelper dbHelper;
    protected Timer sample_rate_timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = DBHelper.getInstance(this);
        locations = Location.getAll(dbHelper);
        lat_et = (EditText)findViewById(R.id.lat_editText);
        lon_et = (EditText)findViewById(R.id.lon_editText);
        search_btn = (Button)findViewById(R.id.search_btn);
        address_tv = (TextView)findViewById(R.id.address_text);

        location_lv = (ListView)findViewById(R.id.location_list);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"interval");

            }
        },
                SAMPLE_DELAY,
                SAMPLE_RATE
        );

        String[] from = {appDB.LocationsEntry.LAT, appDB.LocationsEntry.LON, appDB.LocationsEntry.TIME_STAMP};
        int[] to = {R.id.lbl_lat, R.id.lbl_lng, R.id.lbl_time};
        location_adapter = new SimpleCursorAdapter(this, R.layout.location_item_layout, null, from, to);
        location_item = getLayoutInflater().inflate(R.layout.location_item_layout, null);
        location_lv.addHeaderView(location_item);

        location_lv.setAdapter(location_adapter);
        location_lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "clicked position: " +position);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
