package com.patrykstryczek.secondtry.model;

import android.content.DialogInterface;
import android.view.View;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admiral on 15.05.16.
 */
public class KnownNetwork extends RealmObject {
    private boolean isSelected;
    private String ssid;
    @PrimaryKey
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

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnownNetwork that = (KnownNetwork) o;

        if (isSelected != that.isSelected) return false;
        if (ssid != null ? !ssid.equals(that.ssid) : that.ssid != null) return false;
        if (bssid != null ? !bssid.equals(that.bssid) : that.bssid != null) return false;
        if (routerXPosition != null ? !routerXPosition.equals(that.routerXPosition) : that.routerXPosition != null)
            return false;
        if (routerYPosition != null ? !routerYPosition.equals(that.routerYPosition) : that.routerYPosition != null)
            return false;
        return rssiValue != null ? rssiValue.equals(that.rssiValue) : that.rssiValue == null;

    }

    @Override
    public int hashCode() {
        int result = (isSelected ? 1 : 0);
        result = 31 * result + (ssid != null ? ssid.hashCode() : 0);
        result = 31 * result + (bssid != null ? bssid.hashCode() : 0);
        result = 31 * result + (routerXPosition != null ? routerXPosition.hashCode() : 0);
        result = 31 * result + (routerYPosition != null ? routerYPosition.hashCode() : 0);
        result = 31 * result + (rssiValue != null ? rssiValue.hashCode() : 0);
        return result;
    }
}

