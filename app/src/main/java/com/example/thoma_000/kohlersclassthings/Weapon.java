package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 2/6/2015.
 */
public class Weapon extends Equipment{
    public enum WeaponType {SWORD,BAT}
    int damage;
    int size;
    WeaponType weaponType;
    public Weapon(Bitmap bitmap, float xPos, float yPos, int width, int height,WeaponType weaponType,EquipmentSlot equipmentSlot ){ //random armor

        super(bitmap,xPos,yPos,width,height,equipmentSlot);
        this.weaponType = weaponType;


        this.equipmentType=EquipmentType.WEAPON;

        }
    private void createWeapon(){
        switch (weaponType){


            case SWORD:
                name = "sharp object";
                damage = 25;
                size = 3;
                break;
            case BAT:
                name = "round beating stick";
                damage = 15;
                size = 2;
                break;
        }

    }

}
