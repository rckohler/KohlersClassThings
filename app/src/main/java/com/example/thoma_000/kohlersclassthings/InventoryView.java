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
 * Created by thoma_000 on 2/23/2015.
 */
public class InventoryView extends View {

    Bitmap dudeBitmap, leatherbootBitmap,leatherlegsBitmap,ironlegsBitmap,ironhelmetBitmap,ironchestBitmap,chainchestBitmap,chainhelmetBitmap,chainlegsBitmap,swordlongBitmap;
    Bitmap backgroundimageBitmap;
    Bitmap axeBitmap;
    Bitmap poisonBitmap;
    Bitmap radiationBitmap;
    Bitmap fishBitmap;
    Bitmap internetBitmap;
    Bitmap boxBitmap;
    Bitmap leatherhatBitmap;
    ClassProject main;
    float screenWidth,screenHeight;
    ItemGrouping backpack, equippedItems;
    RectF backgroundBounds;

    public InventoryView(Context context) {

        super(context);
        main = (ClassProject) context;
        screenHeight =main.screenHeight;
        screenWidth= main.screenWidth;
        loadPictures();
        createBackpack();
        createEquippedItemGrouping();
        createStartingArmor();

        equippedItems.setOther(backpack);
        backpack.setOther(equippedItems);
    }

    private void createBackpack() {//buggy come back here....
        //for loop
        ObjectBox slotInBackpack;
        Vector<ObjectBox> equipmentBoxes = new Vector<>();
        for (int i = 0; i < 6; i++) {
            //i think that it is not showing any boxes because we never told
            slotInBackpack = new ObjectBox(boxBitmap, .08f * screenWidth, .09f * screenHeight * (1 * +i) + screenHeight * .2f, (int) (screenWidth * .12), (int) (screenWidth * .12), Equipment.EquipmentSlot.BACKPACK);
            equipmentBoxes.add(slotInBackpack);
            slotInBackpack = new ObjectBox(boxBitmap, 0.92f * screenWidth, .09f * screenHeight * (1 * +i) + screenHeight * .2f, (int) (screenWidth * .12), (int) (screenWidth * .12), Equipment.EquipmentSlot.BACKPACK);
            equipmentBoxes.add(slotInBackpack);// I think we should only have one of these at the very end after the slotInBackpack section kinda like wga we did with the object boxes down below
        }
        backpack = new ItemGrouping(equipmentBoxes);
    }
    private void createEquippedItemGrouping(){
        Vector<ObjectBox> equipmentBoxes = new Vector<>();
        ObjectBox head,legs,rarms,larms,body;//Should we move this up above  the for and below the vector so the clot in backpack knows that it needs to have the rarms,larms etc...
        //i think we should pick between these bitmaps or the one above so it is not trying to create boxes that already exist
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
        equippedItems = new ItemGrouping(equipmentBoxes);
    }
    public void loadPictures(){
        backgroundBounds = new RectF(0,0,screenWidth,.9f*screenHeight);
      //  dudeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dude);
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
    private void createStartingArmor() {
        createArmor(Equipment.EquipmentSlot.HEAD, Armor.ArmorType.HEAD, Armor.MaterialType.IRON);
        createArmor(Equipment.EquipmentSlot.HEAD, Armor.ArmorType.HEAD, Armor.MaterialType.LEATHER);
        createArmor(Equipment.EquipmentSlot.BODY, Armor.ArmorType.BODY, Armor.MaterialType.IRON);
        createArmor(Equipment.EquipmentSlot.LEGS, Armor.ArmorType.LEGS, Armor.MaterialType.LEATHER);
        createArmor(Equipment.EquipmentSlot.LEGS, Armor.ArmorType.LEGS, Armor.MaterialType.IRON);
        createArmor(Equipment.EquipmentSlot.LEGS, Armor.ArmorType.LEGS, Armor.MaterialType.CHAIN);
    }
    private void createArmor(Equipment.EquipmentSlot equipmentSlot,Armor.ArmorType armorType,Armor.MaterialType materialType){
        Armor armor = new Armor(axeBitmap,400,300,100,100, armorType,materialType,equipmentSlot);
        sendBitmapToEquipment(armor);
        backpack.startingPlacementOfItem(armor);

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


    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                float clickX = event.getX();
                float clickY = event.getY();
                backpack.processClick(clickX,clickY);
                equippedItems.processClick(clickX,clickY);
                break;


        }
        return true;
    }
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgroundimageBitmap, null, backgroundBounds, null);
        backpack.drawSelf(canvas);
        equippedItems.drawSelf(canvas);
        invalidate();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            System.err.println("PlanetHopView.onDraw error");
        }
    }
}