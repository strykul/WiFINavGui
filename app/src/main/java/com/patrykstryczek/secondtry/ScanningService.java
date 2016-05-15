package com.patrykstryczek.secondtry;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;

import com.patrykstryczek.secondtry.model.KnownNetwork;

import java.util.ArrayList;
import java.util.List;



public class ScanningService extends Service {
    private final ScanningServiceBinder binder = new ScanningServiceBinder();

    private WifiManager wifiManager;
    private WiFiReceiver wifiReceiver;
    private ScanResultListener scanResultListener;

    public ScanningService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WiFiReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startScan(ScanResultListener listener){
        this.scanResultListener = listener;
        wifiManager.startScan();
    }

    public class WiFiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifiManager.getScanResults();
            List<KnownNetwork> knownNetworks = new ArrayList<>(wifiScanList.size());
            for (ScanResult result : wifiScanList){
                knownNetworks.add(new KnownNetwork(result.SSID, result.BSSID, result.level ));
            }

            if (scanResultListener != null){
                scanResultListener.onScanResult(knownNetworks);
                scanResultListener = null;
            }
        }
    }

    public class ScanningServiceBinder extends Binder{
        public ScanningService getService() {
            return ScanningService.this;
        }

    }


    public interface ScanResultListener {
        void onScanResult(List<KnownNetwork> results);
    }
}
