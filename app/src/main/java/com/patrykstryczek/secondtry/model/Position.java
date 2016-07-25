package com.patrykstryczek.secondtry.model;

/**
 * Created by admiral on 25.07.16.
 */
public class Position {
    private float Xposition;
    private float Yposition;


    public Position(float xposition, float yposition) {
        Xposition = xposition;
        Yposition = yposition;
    }

    public float getYposition() {
        return Yposition;
    }

    public float getXposition() {
        return Xposition;
    }

    public void setXposition(float xposition) {
        Xposition = xposition;
    }

    public void setYposition(float yposition) {
        Yposition = yposition;
    }
}
