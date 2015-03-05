package com.example.thoma_000.kohlersclassthings;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by RCK on 2/20/2015.
 */

public class ItemGrouping {
    Vector<ObjectBox> boxes = new Vector<>();
    ItemGrouping other;
    public ItemGrouping(Vector<ObjectBox> boxes){
        this.boxes = boxes;
    }
    public void setOther(ItemGrouping other)
    {
        this.other = other;
    }
    //backpack.takeItemFromBox(givingBox);
    public void startingPlacementOfItem(Equipment item){
        ObjectBox matchingBox = returnMatchingBox(item);
        matchingBox.equipItem(item);
    }
    public void takeItemFromBox(ObjectBox givingBox){
        //check for matching slot
        ObjectBox matchingBox = returnMatchingBox(givingBox.heldEquipment);
        Equipment swappedEquipment = null;
        if (matchingBox != null) {
            if (matchingBox.heldEquipment != null) {
                swappedEquipment = matchingBox.heldEquipment;
            }
            matchingBox.equipItem(givingBox.heldEquipment);
        }
        if(swappedEquipment!=null)
            givingBox.equipItem(swappedEquipment);
        else
        {
            givingBox.isOccupied = false;
            givingBox.heldEquipment = null;
        }
        //
    }
    public void processClick(float clickX, float clickY){
        ObjectBox clickedBox = returnClickedBox(clickX,clickY);
        if(clickedBox !=null) {
            if (clickedBox.heldEquipment != null) {
                other.takeItemFromBox(clickedBox);
                System.out.println("asking other to take box");
            }
        }
    }
    private ObjectBox returnMatchingBox(Equipment item){
        ObjectBox ret = null;
        for (int i = 0; i < boxes.size(); i++){
            if(boxes.elementAt(i).equipmentSlot == item.equipmentSlot || (boxes.elementAt(i).equipmentSlot== Equipment.EquipmentSlot.BACKPACK && boxes.elementAt(i).heldEquipment==null)){
                ret = boxes.elementAt(i);
            }
        }
        return ret;
    }

    private ObjectBox returnClickedBox(float clickX, float clickY){
        ObjectBox ret = null;
        for (int i = 0; i < boxes.size(); i++){
            if(boxes.elementAt(i).isClicked(clickX,clickY)){
                ret = boxes.elementAt(i);
                ret.processClick();
            }
        }
        return ret;
    }
    public void drawSelf(Canvas canvas){
        for(int i = 0; i < boxes.size(); i++){
            boxes.elementAt(i).drawSelf(canvas);
            if(boxes.elementAt(i).heldEquipment!=null) {
                boxes.elementAt(i).heldEquipment.drawSelf(canvas);
            }
        }
    }

}