package com.patrykstryczek.secondtry;

import com.patrykstryczek.secondtry.model.KnownNetwork;
import com.patrykstryczek.secondtry.model.Position;
import com.patrykstryczek.secondtry.DistanceCalculator;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by admiral on 25.07.16.
 */
public class Calculations {

    static float xposition;
    static float yposition;
    private static Position position = new Position(xposition, yposition);
    private DistanceCalculator dc = new DistanceCalculator();

    public Position positionOfUser(RealmResults<KnownNetwork> results){
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Double> powereddistances = new ArrayList<>();
        //User Position
        double va;
        //Calculation ingredients
        double vb;
        ArrayList<Double> xpos = new ArrayList<>();
        ArrayList<Double> poweredxpos = new ArrayList<>();
        ArrayList<Double> ypos = new ArrayList<>();
        ArrayList<Double> poweredypos = new ArrayList<>();
        Realm realm;




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

            
            xpos.add(n,(double)results.get(n).getRouterXPosition());
            poweredxpos.add(n,Math.pow(xpos.get(n),2));
            ypos.add(n,(double)results.get(n).getRouterYPosition());
            poweredypos.add(n,Math.pow(ypos.get(n),2));

            distances.add(n, dc.DistanceFromRSSI(results.get(n).getRssiValue()));
            powereddistances.add(n,Math.pow(distances.get(n),2));
        }
        va = ((powereddistances.get(1)-powereddistances.get(2))-(poweredxpos.get(1)-poweredxpos.get(2))-(poweredypos.get(1)-poweredypos.get(2)))/2d;
        vb = ((powereddistances.get(1)-powereddistances.get(0))-(poweredxpos.get(1)-poweredxpos.get(0))-(poweredypos.get(1)-poweredypos.get(0)))/2d;
        yposition = (float) (((vb) * (xpos.get(2) - xpos.get(1)) - ((va) * (xpos.get(0) - xpos.get(1)))) / (((ypos.get(0) - ypos.get(1)) * (xpos.get(2) - xpos.get(1))) - ((ypos.get(2) - ypos.get(1)) * (xpos.get(0) - xpos.get(1)))));
        xposition = (float) ((va-(yposition*(ypos.get(2)-ypos.get(1))))/(xpos.get(2)-xpos.get(1)));
        position.setXposition(xposition);
        position.setYposition(yposition);


        return position;
    }

}
