package com.patrykstryczek.secondtry.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.patrykstryczek.secondtry.R;
import com.patrykstryczek.secondtry.SettingsActivity;
import com.patrykstryczek.secondtry.model.KnownNetwork;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admiral on 15.05.16.
 */




public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private List<KnownNetwork> networkList = new ArrayList<>();
    private Activity activity;

    public SettingsAdapter(Context context) {
        this.activity = activity;
    }

    public void setItems(List<KnownNetwork> itemList){
        networkList.clear();
        //TODO Get clicked
        networkList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_settings, parent,
                false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      final KnownNetwork item = networkList.get(position);

        holder.checkBox.setChecked(item.isSelected());
        holder.textView.setText(item.getSsid());
        holder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    };

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

    private View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }


        //TODO - Dialog for position setting


    };

    private void showRouterPositionSettings(){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.router_position_alert, null);
        final android.widget.EditText xposition = (EditText) view.findViewById(R.id.x_position);
        final android.widget.EditText yposition = (EditText) view.findViewById(R.id.y_position);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        builder.setMessage(R.string.position_insert)
                .setTitle(R.string.position);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO - set positions

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

    }
}
