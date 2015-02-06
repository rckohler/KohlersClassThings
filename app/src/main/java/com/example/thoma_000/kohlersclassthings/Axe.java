package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/27/2015.
 */
public class Axe extends DrawableObject{
    Dude player;
    public Axe (Bitmap bitmap, float xPos, float yPos, int width, int height,Dude player) {
        super(bitmap, xPos, yPos, width, height);
        this.player = player;
    }
    @Override
    public void processClick(){
        teleportTO(player.xPos,player.yPos);
        System.out.println("i am a Axe");

    }
}
