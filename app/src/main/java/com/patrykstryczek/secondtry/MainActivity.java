package com.patrykstryczek.secondtry;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.patrykstryczek.secondtry.model.KnownNetwork;
import com.patrykstryczek.secondtry.model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    private Integer NaviPoints = 3;
    private Calculations calculations = new Calculations();
    private CanvasView my_canvas;
    private ScanningService scanningService;
    private RealmResults<KnownNetwork> scanningResults;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindService(new Intent(this, ScanningService.class), connection, Context.BIND_AUTO_CREATE);
        my_canvas  = (CanvasView) findViewById(R.id.my_canvas);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm realm = Realm.getDefaultInstance();

        scanningResults = realm.where(KnownNetwork.class)
                .equalTo("isSelected", true).findAll();

    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();


    }

    private ServiceConnection connection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            scanningService = ((ScanningService.ScanningServiceBinder) service).getService();
            startScanning();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            scanningService = null;

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Launching other activity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.load_img){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            //intent.setType("*/*");      //all files
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            try {
                startActivityForResult(Intent.createChooser(intent, "Select a File to Load"), FILE_SELECT_CODE);
            } catch (android.content.ActivityNotFoundException ex) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(FILE_SELECT_CODE, resultCode, data);
        if(resultCode==RESULT_CANCELED)
        {
            // action cancelled
        }
        if(resultCode==RESULT_OK) {
            Uri selectedimg = data.getData();

            try {
                ImageView imageView = (ImageView) findViewById(R.id.map_loaded);
                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showSettingsAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.show();

    }
    private void startScanning(){
        if (scanningService != null) {
            scanningService.startScan(new ScanningService.ScanResultListener() {
                @Override
                public void onScanResult(List<KnownNetwork> results) {
                    List<KnownNetwork> updatedNetworks = new ArrayList<KnownNetwork>();

                    for(KnownNetwork network : results){
                        if(scanningResults.contains(network)){
                            updatedNetworks.add(network);
                        }
                    }
                    Position userCurr = calculations.positionOfUser(updatedNetworks);
                    my_canvas.updatePositionOfUser(userCurr);
                    startScanning();
                }
            });
        }

    }




}
