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
    Bitmap dudeBitmap, leatherbootBitmap,leatherlegsBitmap,ironlegsBitmap,ironhelmetBitmap,ironchestBitmap,chainchestBitmap,chainhelmetBitmap,chainlegsBitmap,swordlongBitmap;
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
        screenHeight =main.screenHeight;
        screenWidth= main.screenWidth;
        equipment = new Vector<>();
        loadPictures();
        createBackpack();
        createBackgroundImage();

        backgroundBounds = new RectF(0,0,screenWidth,.9f*screenHeight);
        createObjectBox();

        createAllEquipment();
        createWeapons();
        //createEquipment(Equipment.EquipmentSlot.HEAD,Armor.ArmorType.HEAD, Armor.MaterialType.IRON);



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
        if (selectedObject != null && !selectedObject.isEquipped)
            for (int i = 0; i < equipmentBoxes.size(); i++) {
                ObjectBox currentBox = equipmentBoxes.elementAt(i);
                if (selectedObject.equipmentSlot == currentBox.equipmentSlot && !currentBox.isOccupied) {


                    selectedObject.teleportTO(currentBox.xPos, currentBox.yPos);
                    currentBox.isOccupied = true;
                    selectedObject.isEquipped = true;
                }
                if (selectedObject.isEquipped) {
                    System.out.println("if i were smart i would be in a backpack");
                }
                if (!objectClicked)
                    ;
                //player.teleportTO(clickX, clickY);


            }
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
            boxBitmap =BitmapFactory.decodeResource(getResources(),R.drawable.box);
            backgroundimageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.backgroundimage);
            leatherlegsBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.leatherlegs);
            leatherhatBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.leatherhat);
            leatherbootBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.leatherboot);

            ironhelmetBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ironhelmet);
            ironlegsBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ironlegs);
            ironchestBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ironchest);

            chainchestBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.chainchest);
            chainhelmetBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.chainhelmet);
            chainlegsBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.chainlegs);

            swordlongBitmap =BitmapFactory.decodeResource(getResources(),R.drawable.swordlong);
    }
    private void createBackpack(){
        //for loop
        ObjectBox slotInBackpack;
        for (int i = 0;i<6;i++) {
            slotInBackpack = new ObjectBox(boxBitmap, .08f * screenWidth, .09f * screenHeight * (1 * +i)+ screenHeight * .2f, (int) (screenWidth * .12), (int) (screenWidth * .12), Equipment.EquipmentSlot.BACKPACK);
            equipmentBoxes.add(slotInBackpack);
            slotInBackpack = new ObjectBox(boxBitmap, 0.92f * screenWidth, .09f * screenHeight * (1 * +i)+ screenHeight * .2f, (int) (screenWidth * .12), (int) (screenWidth * .12), Equipment.EquipmentSlot.BACKPACK);
            equipmentBoxes.add(slotInBackpack);
        }



    }




    private void createDude() {
        Dude dude = new Dude(dudeBitmap, 50, 50, 100, 100);
        //equipment.add(dude);
       player = dude;
    }

    private void createWeapons(){
        Weapon swordlong = new Weapon (leatherlegsBitmap,75,75,100,100, Weapon.WeaponType.SWORD, Equipment.EquipmentSlot.RARMS);
        swordlong.bitmap = swordlongBitmap;
        equipment.add(swordlong);


    }

    private void createBackgroundImage(){

        BackgroundImage backgroundimage = new BackgroundImage(backgroundimageBitmap, 50,50,100,100 );


        }



    private void createAllEquipment(){
       createEquipment(Equipment.EquipmentSlot.BODY, Armor.ArmorType.BODY, Armor.MaterialType.IRON);
       createEquipment(Equipment.EquipmentSlot.HEAD, Armor.ArmorType.HEAD, Armor.MaterialType.IRON);
       createEquipment(Equipment.EquipmentSlot.LEGS, Armor.ArmorType.LEGS, Armor.MaterialType.IRON);


    }







    private void createEquipment(Equipment.EquipmentSlot equipmentSlot,Armor.ArmorType armorType,Armor.MaterialType materialType){
        Armor armor = new Armor(axeBitmap,400,300,100,100, armorType,materialType,equipmentSlot);
        sendBitmapToEquipment(armor);


        equipment.add(armor);

    }

    private void createObjectBox(){
        ObjectBox head,legs,rarms,larms,body;
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
        legs = new ObjectBox(boxBitmap,screenWidth*.5f,screenHeight*.60f,(int)(screenWidth*.2),(int)(screenHeight*.16),Equipment.EquipmentSlot.LEGS);



        equipmentBoxes.add(rarms);
        equipmentBoxes.add(larms);
        equipmentBoxes.add(body);
        equipmentBoxes.add(head);
        equipmentBoxes.add(legs);


    }
    private void sendBitmapToEquipment(Equipment equipment){
        switch (equipment.bitmapEquipment){

            case LEATHERHAT:
                equipment.bitmap = leatherhatBitmap;
                break;
            case CHAINHAT:
                equipment.bitmap = chainhelmetBitmap;
                break;
            case IRONHAT:
                equipment.bitmap = ironhelmetBitmap;
                break;
            case IRONCHEST:
                equipment.bitmap = ironchestBitmap;
                break;
            case CHAINCHEST:
                equipment.bitmap = chainchestBitmap;
                break;
            case LEATHERBOOTS:
                equipment.bitmap = leatherbootBitmap;
                break;
            case IRONLEGS:
                equipment.bitmap = ironlegsBitmap;
                break;
            case CHAINLEGS:
                equipment.bitmap = chainlegsBitmap;
                break;
            case LEATHERLEGS:
                equipment.bitmap = leatherlegsBitmap;
                break;

        }
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