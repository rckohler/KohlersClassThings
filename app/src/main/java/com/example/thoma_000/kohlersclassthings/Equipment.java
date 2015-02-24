package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * Created by thoma_000 on 1/30/2015.
 */
public class Equipment extends DrawableObject {


    //global variables
    String name;
    int weight;
    boolean isEquipped;

    public enum EquipmentSlot {LEGS, HEAD, BODY, RARMS, LARMS, WEAPON, BACKPACK}

    int durability;

    public enum BitmapEquipment {LEATHERHAT, CHAINHAT, IRONHAT, IRONCHEST, CHAINCHEST, LEATHERBOOTS, IRONLEGS, CHAINLEGS, LEATHERLEGS, LEATHERCHEST}//add everything at some point

    public enum EquipmentType {WEAPON, ARMOR}

    public EquipmentSlot equipmentSlot;
    public EquipmentType equipmentType;
    public BitmapEquipment bitmapEquipment;

    public Equipment(Bitmap bitmap, float xPos, float yPos, int width, int height, Equipment.EquipmentSlot equipmentSlot) {

        super(bitmap, xPos, yPos, width, height);
        isEquipped = false;
        this.equipmentSlot = equipmentSlot;

    }

    @Override
    public void processClick() {

       // System.out.println("i am da internet");

    }
    public void equip(ObjectBox targetBox){
        teleportTo(targetBox);
        targetBox.isOccupied = true;
        targetBox.heldEquipment = this;
    }
}


