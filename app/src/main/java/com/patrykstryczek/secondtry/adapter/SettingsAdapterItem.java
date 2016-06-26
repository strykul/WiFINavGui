package com.patrykstryczek.secondtry.adapter;

import android.view.View;

import com.patrykstryczek.secondtry.model.KnownNetwork;

/**
 * Created by admiral on 15.05.16.
 */
public class SettingsAdapterItem extends KnownNetwork {
    private View.OnClickListener onClickListener;

    public SettingsAdapterItem(KnownNetwork knownNetwork, View.OnClickListener onClickListener ){
        super(false,knownNetwork.getSsid(), knownNetwork.getBssid(), knownNetwork.getRouterXPosition(),
                knownNetwork.getRouterYPosition(), knownNetwork.getRssiValue() );
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
