package com.patrykstryczek.secondtry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.patrykstryczek.secondtry.model.KnownNetwork;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by patrykstryczek on 22.09.16.
 */

public class CanvasView extends View {

    final static Integer radius = 5;

    public CanvasView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CanvasView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs,0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, Object o, int i) {
    //Nothing to do here
    }



    protected void onDraw(Canvas canvas) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<KnownNetwork> results = realm.where(KnownNetwork.class)
                .equalTo("isSelected", true).findAll();
        Paint pedzel = new Paint();
        //Drawing navipoints
        for (int n = 0; n < results.size(); n++) {
            KnownNetwork curr = results.get(n);
            canvas.drawLine(curr.getRouterXPosition() - 50f, curr.getRouterYPosition(),
                    curr.getRouterXPosition() + 50f, curr.getRouterYPosition(), pedzel);
            canvas.drawLine(curr.getRouterXPosition(), curr.getRouterYPosition() - 50f,
                    curr.getRouterXPosition(), curr.getRouterYPosition() + 50f, pedzel);

            canvas.drawCircle(curr.getRouterXPosition(), curr.getRouterYPosition(), radius, pedzel);
        }
    }
}
