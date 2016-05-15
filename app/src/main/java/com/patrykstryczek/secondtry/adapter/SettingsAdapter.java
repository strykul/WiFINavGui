package com.patrykstryczek.secondtry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.patrykstryczek.secondtry.R;
import com.patrykstryczek.secondtry.model.KnownNetwork;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admiral on 15.05.16.
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private List<SettingsAdapterItem> networkList = new ArrayList<>();
    private Context context;

    public SettingsAdapter(Context context) {
        this.context = context;
    }

    public void setItem(List<SettingsAdapterItem> itemList){
        networkList.clear();
        networkList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_settings, parent,
                false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingsAdapterItem item = networkList.get(position);

        holder.checkBox.setChecked(item.isSelected());
        holder.textView.setText(item.getSsid());
        holder.button.setOnClickListener(item.getOnClickListener());

    }

    @Override
    public int getItemCount() {
        return networkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_box_wifi);
            textView = (TextView) itemView.findViewById(R.id.wifi_name);
            button = (Button) itemView.findViewById(R.id.wifi_settings);
        }
    }


}
