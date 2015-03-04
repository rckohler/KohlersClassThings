package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/29/2015.
 */
public class ObjectBox extends DrawableObject{

    Equipment.EquipmentSlot equipmentSlot;
    boolean isOccupied;
    Equipment heldEquipment;
    public ObjectBox (Bitmap bitmap, float xPos, float yPos, int width, int height, Equipment.EquipmentSlot equipmentSlot) {
        super(bitmap, xPos, yPos, width, height);
        this.equipmentSlot = equipmentSlot;}

    public void moveItemTo (ObjectBox other){ // looks bad needs to be revised...

        isOccupied = false;
        other.isOccupied = true;
        heldEquipment.bounds = other.bounds;
        heldEquipment.teleportTo(other);
        heldEquipment = null;

      // isSelected.equipment.moveTo ObjectBox && Equipment.EquipmentSlot if(objectbox.!isOccupied);
    }
    public void equipItem(Equipment item){
        if(item != null) {
            heldEquipment = item;
            isOccupied = true;
            item.teleportTo(this);
            item.bounds = bounds;
        }
    }



    @Override
    public void processClick(){

      //  System.out.println("i am da internet");

    }




}
