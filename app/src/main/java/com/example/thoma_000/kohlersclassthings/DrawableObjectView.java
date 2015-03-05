package com.example.thoma_000.kohlersclassthings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

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
        //loop through all object Boxes\
        ObjectBox lastClickedBox = null;
        ObjectBox emptyBackpackBox = null;
        for (int i = 0; i < equipmentBoxes.size(); i++) {//unequip  put in inventory
            ObjectBox currentBox = equipmentBoxes.elementAt(i);

            if (currentBox.isClicked(clickX, clickY)) {
                lastClickedBox = currentBox;
            }
            if (currentBox.equipmentSlot == Equipment.EquipmentSlot.BACKPACK && !currentBox.isOccupied) {
                //empty backpack slot would = false
                currentBox = emptyBackpackBox;
            }
        }
        //check if clicked


        //if clicked check if backpack or equipBox
        if (lastClickedBox !=null)
            if (lastClickedBox.equipmentSlot == Equipment.EquipmentSlot.BACKPACK) {
                handleBackpackClick(lastClickedBox); //IF BACKPACK handle items
            } else {   //IF equipBox unequip
                if(emptyBackpackBox!=null){
                    ;
                }
            }



        }



                // create function swapItems

                // create function unequip



    private void handleBackpackClick2(ObjectBox backPackSlot) {
        //swap (H1,B)
        //{
        //1) find appropriate destination for H1
        //does backpack slot have held equipment and does the destination box hold equipment
        if (backPackSlot.heldEquipment == null){
            Equipment newlyEquipped = backPackSlot.heldEquipment;
            ObjectBox destinationBox = null;
            for (int i = 0; i < equipmentBoxes.size(); i++) {//unequip  put in inventory
                ObjectBox currentBox = equipmentBoxes.elementAt(i);

                if (currentBox.equipmentSlot == newlyEquipped.equipmentSlot){
                    destinationBox = currentBox;
                }
                //send newly equipped to destination box
                newlyEquipped.teleportTo(destinationBox);
                //tell newly equipped that it is equipped
                newlyEquipped.isEquipped = true;
                //tell destinationbox that it is occupied
                destinationBox.isOccupied = true;


                //send olditem to backpack
                destinationBox.heldEquipment.teleportTo(backPackSlot);
                //tell olditem that is is not equipped
                destinationBox.heldEquipment.isEquipped = false;
                //tell backpack it is occupied
                backPackSlot.isOccupied = true;

                Equipment oldEquipment = destinationBox.heldEquipment;
                if(oldEquipment == null) {


                    destinationBox.heldEquipment.teleportTo(backPackSlot);//THIS IS WHERE WE ARE STOPPED
                    // this is taking the current equipment in the equipment slot and it is sending it to the backpack
                    destinationBox.heldEquipment.isEquipped = false;
                    //
                    backPackSlot.isOccupied = true;

                }

                //MAKE MOVE TO THAT WILL REPLACE TELEPORTO


                }
            //2) Move H1 to D
            newlyEquipped.teleportTo(destinationBox);
        newlyEquipped.isEquipped = true;
        }
        //3) Take H2 for D and put in backpack
    }
    private void handleBackpackClick(ObjectBox backPackSlot) {
        //make sure backpack has an item in it
        Equipment backPackEquipment = backPackSlot.heldEquipment;
        //find destination box
        ObjectBox destinationBox = null;
        if (backPackEquipment != null) {
            for (int i = 0; i < equipmentBoxes.size(); i++){
                if (equipmentBoxes.elementAt(i).equipmentSlot == backPackEquipment.equipmentSlot){
                    destinationBox = equipmentBoxes.elementAt(i);
                }
            }
            //equip backpack item to destination
            //backPackEquipment.equip(destinationBox,backPackSlot);
            //equip destination item to backpack
            //destinationBox.heldEquipment.equip(backPackSlot,destinationBox);
        }
    }

    public void notOccupied(){

        //if the equipment box does not have a picture it is  unoccupied
        //if the inventory box has a picture that means it is occupied
        // if the picture is selected while in the backpack the backpack box is no longer occupied and the object should move to the equipment slot
        // if the equipment slot item is selected the equipment box is no longer occupied
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
   /*    createEquipment(Equipment.EquipmentSlot.BODY, Armor.ArmorType.BODY, Armor.MaterialType.IRON);
       createEquipment(Equipment.EquipmentSlot.HEAD, Armor.ArmorType.HEAD, Armor.MaterialType.IRON);
       createEquipment(Equipment.EquipmentSlot.LEGS, Armor.ArmorType.LEGS, Armor.MaterialType.IRON);
*/

    }







    private void createArmor(Equipment.EquipmentSlot equipmentSlot,Armor.ArmorType armorType,Armor.MaterialType materialType){
        Armor armor = new Armor(axeBitmap,400,300,100,100, armorType,materialType,equipmentSlot);
        ObjectBox destinationBox = null;
        for(int i = 0; i < equipmentBoxes.size(); i++){
            if (equipmentBoxes.elementAt(i).equipmentSlot == armor.equipmentSlot){
                destinationBox = equipmentBoxes.elementAt(i);
            }
        }
        //armor.equip(destinationBox,null);
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