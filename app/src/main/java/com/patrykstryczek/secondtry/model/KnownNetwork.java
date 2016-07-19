package com.patrykstryczek.secondtry.model;

import android.content.DialogInterface;
import android.view.View;

import io.realm.RealmObject;

/**
 * Created by admiral on 15.05.16.
 */
public class KnownNetwork extends RealmObject {
    private boolean isSelected;
    private String ssid;
    private String bssid;
    private Float routerXPosition;
    private Float routerYPosition;
    private Integer rssiValue;

    public KnownNetwork(){};

    public KnownNetwork(String ssid, String bssid, Integer rssiValue){
        this(false, ssid, bssid, null, null, rssiValue);
    }

    public KnownNetwork(boolean isSelected, String ssid, String bssid, Float routerXPosition,
                        Float routerYPosition, Integer rssiValue) {
        this.isSelected = isSelected;
        this.ssid = ssid;
        this.bssid = bssid;
        this.routerXPosition = routerXPosition;
        this.routerYPosition = routerYPosition;
        this.rssiValue = rssiValue;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public Float getRouterXPosition() {
        return routerXPosition;
    }

    public Float getRouterYPosition() {
        return routerYPosition;
    }

    public Integer getRssiValue() {
        return rssiValue;
    }

    public void setRouterXPosition(Float routerXPosition) {
        this.routerXPosition = routerXPosition;
    }

    public void setRouterYPosition(Float routerYPosition) {
        this.routerYPosition = routerYPosition;
    }

}

