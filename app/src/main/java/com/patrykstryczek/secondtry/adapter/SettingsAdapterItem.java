package com.patrykstryczek.secondtry.adapter;

import android.view.View;

import com.patrykstryczek.secondtry.model.KnownNetwork;

/**
 * Created by admiral on 15.05.16.
 */
public class SettingsAdapterItem extends KnownNetwork {
    private View.OnClickListener onClickListener;
    public SettingsAdapterItem(boolean isSelected, String ssid, String bssid, Float routerXPosition,
                               Float routerYPosition, Integer rssiValute, View.OnClickListener onClickListener) {
        super(isSelected, ssid, bssid, routerXPosition, routerYPosition, rssiValute);
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
