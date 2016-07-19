package com.patrykstryczek.secondtry;

import android.provider.CalendarContract;

import com.patrykstryczek.secondtry.model.KnownNetwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admiral on 18.06.16.
 */
public class SelectedNetworkContainer {

    private static final SelectedNetworkContainer INSTANCE = new SelectedNetworkContainer();
    private SelectedNetworkContainer(){
    };

    private Set<KnownNetwork> selectedNetworks = new HashSet<>();

    public void addNetwork(KnownNetwork network){
        selectedNetworks.add(network);
    }

    public List<KnownNetwork> getNetworks(){
        return new ArrayList<>(selectedNetworks);
    }

    public void removeNetwork(KnownNetwork network){
        selectedNetworks.remove(network);
    }
}
