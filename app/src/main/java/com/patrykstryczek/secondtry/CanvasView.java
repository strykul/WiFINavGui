package com.patrykstryczek.secondtry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.test.RenamingDelegatingContext;
import android.util.AttributeSet;
import android.view.View;

import com.patrykstryczek.secondtry.model.KnownNetwork;
import com.patrykstryczek.secondtry.model.Position;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by patrykstryczek on 22.09.16.
 */

public class CanvasView extends View {

    final static Integer radius = 5;
    Position userCurr;
    //MOCK DATA ABOUT THE ROOM
    Integer AX = 65;
    Integer BX = 1015;
    Integer CX = BX;
    Integer DX = AX;
    Integer AY = 229;
    Integer BY = AY;
    Integer CY = 1354;
    Integer DY = CY;


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
        Paint pedzel2 = new Paint();
        pedzel2.setColor(16711681);
        //Drawing navipoints
        for (int n = 0; n < results.size(); n++) {
            KnownNetwork curr = results.get(n);
            if (curr != null && curr.getRouterYPosition() != null && curr.getRouterXPosition() != null) {
                canvas.drawLine(curr.getRouterXPosition() - 50f, curr.getRouterYPosition(),
                        curr.getRouterXPosition() + 50f, curr.getRouterYPosition(), pedzel);
                canvas.drawLine(curr.getRouterXPosition(), curr.getRouterYPosition() - 50f,
                        curr.getRouterXPosition(), curr.getRouterYPosition() + 50f, pedzel);

                canvas.drawCircle(curr.getRouterXPosition(), curr.getRouterYPosition(), radius, pedzel);
            }
        }
        if(userCurr != null) {
            canvas.drawLine(userCurr.getXposition() - 50f, userCurr.getYposition(),
                    userCurr.getXposition() + 50f, userCurr.getYposition(), pedzel);
            canvas.drawLine(userCurr.getXposition(), userCurr.getYposition() - 50f,
                    userCurr.getXposition(), userCurr.getYposition() + 50f, pedzel);
            canvas.drawCircle(userCurr.getXposition(), userCurr.getYposition(), radius, pedzel2);
        }
        //Sciana AB
        canvas.drawLine(AX, AY, BX, BY, pedzel);
        //Sciana BC
        canvas.drawLine(BX, BY, CX, CY, pedzel);
        //Sciana CD
        canvas.drawLine(CX, CY, DX, DY, pedzel);
        //Sciana DA
        canvas.drawLine(DX,DY,AX,AY, pedzel);
    }
    protected void updatePositionOfUser(Position userPosition){
        userCurr = userPosition;
        invalidate();
    }
}
