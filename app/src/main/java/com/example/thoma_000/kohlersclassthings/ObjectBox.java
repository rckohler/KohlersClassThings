package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/29/2015.
 */
public class ObjectBox extends DrawableObject{

    Equipment.EquipmentSlot equipmentSlot;
    public ObjectBox (Bitmap bitmap, float xPos, float yPos, int width, int height, Equipment.EquipmentSlot equipmentSlot) {
        super(bitmap, xPos, yPos, width, height);
        this.equipmentSlot = equipmentSlot;




    }
    @Override
    public void processClick(){

        System.out.println("i am da internet");

    }




}
