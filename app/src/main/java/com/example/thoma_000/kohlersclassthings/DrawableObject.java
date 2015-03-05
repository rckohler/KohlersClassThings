package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by thoma_000 on 1/26/2015.
 */
public class DrawableObject {
    public float xPos;
    public float yPos;
    float width;
    float height;
    Bitmap bitmap;
    RectF bounds;
  //  boolean isSelected;


    public boolean isClicked(float clickX, float clickY) {
        boolean ret = false;
        if (bounds.contains(clickX, clickY)) {
            ret = true;
        }
        return ret;
    }

    public void scaleToBounds(RectF bounds){


this.bounds = bounds;




    }




public void processClick(){

    System.out.println("this is default process click");
}


public DrawableObject(Bitmap bitmap,float xPos,float yPos, int width,int height){
    bounds = new RectF();
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this. bitmap = bitmap;
    this.height = height;

    setBounds();
}

private void setBounds(){
    float left =xPos -.5f*width;
    float right =xPos +.5f*width;
    float top =yPos -.5f*height;
    float bottom =yPos + .5f*height;
    bounds.set(left,top,right,bottom);



}

public void teleportTo(float destinationX,float destinationY){
    xPos = destinationX;
    yPos = destinationY;
    //System.out.println("is selected");
    //System.out.println("is not selected");
    }

public void teleportTo(ObjectBox box){
    teleportTo(box.xPos,box.yPos);
    bounds = box.bounds;



}
public void update(Canvas canvas){
   drawSelf(canvas);

}


public void drawSelf (Canvas canvas){
    //setBounds();
    canvas.drawBitmap(bitmap,null,bounds,null);

    }
}