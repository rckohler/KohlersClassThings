package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/30/2015.
 */
public class Equipment extends DrawableObject {


    //global variables
    String name;
    int weight;
    boolean isEquiped;

    public enum EquipmentSlot {LLEGS, RLEGS, HEAD, BODY, RARMS, LARMS, WEAPON}

    int durability;

    public enum BitmapEquipment {LEATHERHAT, CHAINHAT, IRONHAT}//add everything at some point

    public enum EquipmentType {WEAPON, ARMOR}

    public EquipmentSlot equipmentSlot;
    public EquipmentType equipmentType;
    public BitmapEquipment bitmapEquipment;

    public Equipment(Bitmap bitmap, float xPos, float yPos, int width, int height, Equipment.EquipmentSlot equipmentSlot){

    super(bitmap,xPos,yPos,width,height);
    this.equipmentSlot=equipmentSlot;

        }
        @Override
        public void processClick(){

            System.out.println("i am da internet");

        }






    }
