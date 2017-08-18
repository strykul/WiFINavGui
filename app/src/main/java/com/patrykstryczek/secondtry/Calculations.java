package com.patrykstryczek.secondtry;

import android.util.Log;

import com.patrykstryczek.secondtry.model.KnownNetwork;
import com.patrykstryczek.secondtry.model.Position;
import com.patrykstryczek.secondtry.DistanceCalculator;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

import static java.lang.Math.abs;

/**
 * Created by admiral on 25.07.16.
 */
public class Calculations {

    static float xposition;
    static float yposition;
    private static Position position = new Position(xposition, yposition);
    private DistanceCalculator dc = new DistanceCalculator();

    public Position positionOfUser(List<KnownNetwork> results, Map<String, List<Integer>> rssiHist) {
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Double> poweredDistances = new ArrayList<>();
        //User Position
        double va;
        //Calculation ingredients
        double vb;
        ArrayList<Double> xpos = new ArrayList<>();
        ArrayList<Double> poweredxpos = new ArrayList<>();
        ArrayList<Double> ypos = new ArrayList<>();
        ArrayList<Double> poweredypos = new ArrayList<>();
        List<KnownNetwork> averagePosition = new ArrayList<>();

        //Average value of RSSI
        for (KnownNetwork knownNetwork:results) {
            List<Integer> tempRSSI = new ArrayList<Integer>();
            tempRSSI = rssiHist.get(knownNetwork.getBssid());
            int average = 0;
            for (Integer i : tempRSSI) {
                average += i;
            }
            average /= tempRSSI.size();
            Log.d("RSSI known powers size", String.valueOf(tempRSSI.size()));
            Log.d("Average RSSI of ", knownNetwork.getSsid() + " : " + average);

//            knownNetwork.setRssiValue(average);
        }




        //test data for point in (3,3)
//        xpos.add(0,4d);
//        xpos.add(1,0.000001d);
//        xpos.add(2,0.000002d);
//        ypos.add(0,0.000003d);
//        ypos.add(1,5d);
//        ypos.add(2,0.000004d);5d

//        distances.add(0,3.162d);
//        distances.add(1,3.605d);
//        distances.add(2,4.242d);
        for (int n = 0; n < results.size(); n++) {

            if (results != null) {
                xpos.add(n, (double) results.get(n).getRouterXPosition());
                poweredxpos.add(n, Math.pow(xpos.get(n), 2));
                ypos.add(n, (double) results.get(n).getRouterYPosition());
                poweredypos.add(n, Math.pow(ypos.get(n), 2));

                distances.add(n, dc.DistanceFromRSSI(results.get(n).getRssiValue()));
                Log.d("DIST","N:  " + results.get(n).getSsid()  + "DIST:  " + dc.DistanceFromRSSI(results.get(n).getRssiValue())) ;
                poweredDistances.add(n, Math.pow(distances.get(n), 2));
            }
        }
        va = ((poweredDistances.get(1)-poweredDistances.get(2))-(poweredxpos.get(1)-poweredxpos.get(2))-(poweredypos.get(1)-poweredypos.get(2)))/2d;
        vb = ((poweredDistances.get(1)-poweredDistances.get(0))-(poweredxpos.get(1)-poweredxpos.get(0))-(poweredypos.get(1)-poweredypos.get(0)))/2d;
        yposition = (float) (((vb) * (xpos.get(2) - xpos.get(1)) - ((va) * (xpos.get(0) - xpos.get(1)))) / (((ypos.get(0) - ypos.get(1)) * (xpos.get(2) - xpos.get(1))) - ((ypos.get(2) - ypos.get(1)) * (xpos.get(0) - xpos.get(1)))));
        xposition = (float) ((va-(yposition*(ypos.get(2)-ypos.get(1))))/(xpos.get(2)-xpos.get(1)));
        xposition = abs(xposition);
        position.setXposition(xposition);
        Log.d("UserPosition", " x = " + xposition);
        yposition = abs(yposition);
        position.setYposition(yposition);
        Log.d("UserPosition", " y = " + yposition);



        return position;
    }



}
