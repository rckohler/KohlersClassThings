package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/29/2015.
 */
public class Armor extends Equipment {
    //global variables
    int size;
    int armorValue;
    float blockChance;


    boolean isEquiped;

    public enum HelmetType{LEATHER,CHAIN,IRON}
    public enum ArmorType{LEGS,HEAD,BODY,ARMS }

    public Armor (Bitmap bitmap, float xPos, float yPos, int width, int height) { //random armor
        super(bitmap, xPos, yPos, width, height);
        this.equipmentType = EquipmentType.ARMOR;
        createHelmet();
    }

    private void createHelmet() {
        HelmetType helmetType = HelmetType.LEATHER;

        switch (helmetType) {


            case LEATHER:
                createLeatherHelmet();
                break;
            case CHAIN:
                break;
            case IRON:
                break;
        }
    }
        private void createLeatherHelmet(){
            int armorValue = 1;
            String name = "old leather hat";
            float blockChance= 2;
            bitmapEquipment = BitmapEquipment.LEATHERHAT;
    }

    @Override
    public void processClick(){;

        System.out.println("i am da internet");

    }







}
