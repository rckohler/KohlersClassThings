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

    public void moveTo (ObjectBox other){
        //move selected equipment to the other box/destination
        isOccupied = false;
        other.isOccupied = true;
        heldEquipment.teleportTo(other);
        if(other.equipmentSlot== Equipment.EquipmentSlot.BACKPACK)
            heldEquipment.isEquipped = false;
        else
        heldEquipment.isEquipped=true;// WE ADDED THIS IN ON TUESDAY 2/17/15  COME BACK TO THIS

        //is other box where i am going is that box a backpack if it is im equiped if not im not equiped
        //check to see if the box you are moving to has something inside of it

        //once the equipment is in other box make the box it was previously in empty
        //have the previous become empty once the equipment is moved to the new box
        //if it is selected again and is not in an equipment slot move it from the inventory to the new equipment slot

        //isSelected.equipment.moveTo ObjectBox if(objectbox.!isOccupied);


        isOccupied = false;

      // isSelected.equipment.moveTo ObjectBox && Equipment.EquipmentSlot if(objectbox.!isOccupied);
    }
    public void equipItem(Equipment item){
        if(item != null){
            heldEquipment = item;
            isOccupied = true;
        }
    }



    @Override
    public void processClick(){

      //  System.out.println("i am da internet");

    }




}
