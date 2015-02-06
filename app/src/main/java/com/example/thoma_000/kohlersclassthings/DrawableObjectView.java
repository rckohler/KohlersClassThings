package com.example.thoma_000.kohlersclassthings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by thoma_000 on 1/26/2015.
 */


public class DrawableObjectView extends View {
   float screenWidth,screenHeight;
    Vector<Equipment> equipment;
    Vector<ObjectBox> equipmentBoxes = new Vector<>();
    Dude player;
    Bitmap dudeBitmap;
    Bitmap backgroundimageBitmap;
    Bitmap axeBitmap;
    Bitmap poisonBitmap;
    Bitmap radiationBitmap;
    Bitmap fishBitmap;
    Bitmap internetBitmap;
    Bitmap boxBitmap;
    Bitmap leatherhatBitmap;
    Equipment selectedObject;
    ClassProject main;
    RectF backgroundBounds;



    public DrawableObjectView(Context context) {
        super(context);
        main = (ClassProject) context;
        equipment = new Vector<>();
        loadPictures();
        createBackgroundImage();

        createDude();
        screenHeight =main.screenHeight;
        screenWidth= main.screenWidth;
       backgroundBounds = new RectF(0,0,screenWidth,.9f*screenHeight);
       // createAxe();
        //createPoison();
       // createRadiation();
       // createFish();
       // createInternet();
       createObjectBox();
        createEquipment();



    }
    private void handleClick(float clickX,float clickY) {
        boolean objectClicked = false;
        for (int i = 0; i < equipment.size(); i++) {//USE THIS A TON

            {
                if (equipment.elementAt(i).isClicked(clickX, clickY)) {
                    equipment.elementAt(i).processClick();
                    objectClicked = true;
                    if (equipment.elementAt(i).isSelected)
                        selectedObject = equipment.elementAt(i);
                    else
                         selectedObject = null;
                }
            }
        }
        if (selectedObject!=null)
        for (int i = 0; i < equipmentBoxes.size(); i++){
           ObjectBox currentBox = equipmentBoxes.elementAt(i);
            if (selectedObject.equipmentSlot==currentBox.equipmentSlot)
                selectedObject.teleportTO(currentBox.xPos,currentBox.yPos);

        }
            if (!objectClicked)
            player.teleportTO(clickX, clickY);


    }
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                float clickX = event.getX();
                float clickY = event.getY();
                handleClick(clickX,clickY);
                break;


        }
        return true;
    }
        public void loadPictures(){
        dudeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dude);
        axeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.axe);
        poisonBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.poison);
        radiationBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.radiation);
        fishBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fish);
        internetBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.internet);
        boxBitmap =BitmapFactory.decodeResource(getResources(),R.drawable.box);
        leatherhatBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.leatherhat);
        backgroundimageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.backgroundimage);

    }

    private void createDude() {
        Dude dude = new Dude(dudeBitmap, 50, 50, 100, 100);
        //equipment.add(dude);
       player = dude;
    }



    private void createBackgroundImage(){

        BackgroundImage backgroundimage = new BackgroundImage(backgroundimageBitmap, 50,50,100,100 );


        }









    private void createEquipment(){
        Armor armor = new Armor(fishBitmap,400,300,100,100);
        sendBitmapToEquipment(armor);
        armor.equipmentSlot = Equipment.EquipmentSlot.HEAD;

        equipment.add(armor);

    }

    private void createObjectBox(){
        ObjectBox head,rlegs,llegs,rarms,larms,body;
       //  if (selectedObject) == null;
       //  if (selectedObject) == head,legs,arms,body;
       //  teleportTo selectedObject = selectedObject;
       // if(selectedObject)== selectedObjectBox teleport selectedObject teleportTo ObjectBox;
       // ObjectBox box = new ObjectBox(boxBitmap, 200, 400, 200, 250);
        //(bitmap,x,y,width,height)
        rarms = new ObjectBox(boxBitmap,screenWidth*.7f,screenHeight*.375f,(int)(screenWidth*.1),(int)(screenHeight*.20), Equipment.EquipmentSlot.RARMS);
        larms = new ObjectBox(boxBitmap,screenWidth*.3f,screenHeight*.375f,(int)(screenWidth*.1),(int)(screenHeight*.20), Equipment.EquipmentSlot.LARMS);
        body = new ObjectBox(boxBitmap,screenWidth*.5f,screenHeight*.4f,(int)(screenWidth*.2),(int)(screenHeight*.25), Equipment.EquipmentSlot.BODY);
        head = new ObjectBox(boxBitmap,screenWidth*.5f,screenHeight*.20f,(int)(screenWidth*.25),(int)(screenHeight*.13), Equipment.EquipmentSlot.HEAD);
        llegs = new ObjectBox(boxBitmap,screenWidth*.45f,screenHeight*.60f,(int)(screenWidth*.1),(int)(screenHeight*.16),Equipment.EquipmentSlot.LLEGS);
        rlegs = new ObjectBox(boxBitmap,screenWidth*.55f,screenHeight*.60f,(int)(screenWidth*.1),(int)(screenHeight*.16),Equipment.EquipmentSlot.RARMS);

        equipmentBoxes.add(rarms);
        equipmentBoxes.add(larms);
        equipmentBoxes.add(body);
        equipmentBoxes.add(head);
        equipmentBoxes.add(llegs);
        equipmentBoxes.add(rlegs);

    }
    private void sendBitmapToEquipment(Equipment equipment){
        switch (equipment.bitmapEquipment){

            case LEATHERHAT:
                equipment.bitmap = leatherhatBitmap;
                break;
            case CHAINHAT:
                break;
            case IRONHAT:
                break;
        }
    }


    private void moveaxeDude(){
        Dude player;





    }




    private void drawDrawableObjects(Canvas canvas) {
        canvas.drawBitmap(backgroundimageBitmap,null,backgroundBounds,null);
        for (int i = 0; i < equipment.size(); i++) {//USE THIS A TON
            equipment.elementAt(i).drawSelf(canvas);
        }
        for (int i = 0; i < equipmentBoxes.size(); i++) {//USE THIS A TON
            equipmentBoxes.elementAt(i).drawSelf(canvas);
        }
    }
    protected void onDraw(Canvas canvas){
    drawDrawableObjects(canvas);




        invalidate();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            System.err.println("PlanetHopView.onDraw error");
        }



    }

}