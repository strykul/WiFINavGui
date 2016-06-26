package com.patrykstryczek.secondtry;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.patrykstryczek.secondtry.adapter.SettingsAdapter;
import com.patrykstryczek.secondtry.adapter.SettingsAdapterItem;
import com.patrykstryczek.secondtry.model.KnownNetwork;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private SettingsAdapter settingsAdapter;
    private ScanningService scanningService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_settings);
        settingsAdapter = new SettingsAdapter(this);
        if (recyclerView != null) {
            recyclerView.setAdapter(settingsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        bindService(new Intent(this, ScanningService.class), connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);

    }

    private ServiceConnection connection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            scanningService = ((ScanningService.ScanningServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            scanningService = null;

        }
    };

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (scanningService != null) {
            scanningService.startScan(new ScanningService.ScanResultListener() {
                @Override
                public void onScanResult(List<KnownNetwork> results) {
                    List<SettingsAdapterItem> scanResults = new ArrayList<>();
                    for (KnownNetwork network : results) {
                        scanResults.add(new SettingsAdapterItem(network, settingsListener);

                    }
                    settingsAdapter.setItems(scanResults);

                }
            });
        }
    }
    private View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TODO - Dialog for position setting

        }
    };
}