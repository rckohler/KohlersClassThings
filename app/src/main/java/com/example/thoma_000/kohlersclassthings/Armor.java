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

    public enum MaterialType{LEATHER,CHAIN,IRON}
    public enum ArmorType{LEGS,HEAD,BODY,ARMS }


    public Armor (Bitmap bitmap, float xPos, float yPos, int width, int height,ArmorType armorType,MaterialType materialType,EquipmentSlot equipmentSlot ) { //random armor
        super(bitmap, xPos, yPos, width, height, equipmentSlot);
        this.equipmentType = EquipmentType.ARMOR;
        createArmor(materialType,armorType);

    }
        private void createArmor(MaterialType materialType, ArmorType armorType){

           switch(armorType){

               case LEGS:
                    createLegArmor(materialType);
                   break;
               case HEAD:
                   createHeadArmor(materialType);
                   break;
               case BODY:
                   createChestArmor(materialType);
                   break;

           }
        }






        private void createLeatherHelmet(){
            int armorValue = 1;
            String name = "old leather hat";
            float blockChance= 2;
            bitmapEquipment = BitmapEquipment.LEATHERHAT;
    }
        private void createChainHelmet(){
            int armorValue = 2;
            String name = "fancy chain hat";
            float blockChance= 3;
            bitmapEquipment = BitmapEquipment.CHAINHAT;
    }
        private void createIronHelmet(){
            int armorValue = 3;
            String name = "shiny sparkling hat";
            float blockChance= 4;
            bitmapEquipment = BitmapEquipment.IRONHAT;
    }
        private void createChainChest(){
            int armorValue = 3;
            String name = "chain shirt";
            float blockChance= 4;
            bitmapEquipment = BitmapEquipment.CHAINCHEST;
    }
        private void createIronChest(){
            int armorValue = 6;
            String name = "shiny sparkling shirt";
            float blockChance= 5;
            bitmapEquipment = BitmapEquipment.IRONCHEST;
    }

        private void createLeatherBoot(){
            int armorValue = 1;
            String name = "old smelly shoes";
            float blockChance= 0;
            bitmapEquipment = BitmapEquipment.LEATHERBOOTS;
    }

        private void createLeatherLegs(){
            int armorValue = 1;
            String name = "leather pants";
            float blockChance= 0;
            bitmapEquipment = BitmapEquipment.LEATHERLEGS;
    }

        private void createIronLegs(){
            int armorValue = 2;
            String name = "old smelly shoes";
            float blockChance= 1;
            bitmapEquipment = BitmapEquipment.IRONLEGS;
    }

        private void createChainLegs(){
            int armorValue = 1;
            String name = "old smelly shoes";
            float blockChance= 0;
            bitmapEquipment = BitmapEquipment.CHAINLEGS;

    }
        private void createLeatherChest() {
            int armorValue = 1;
            String name = "old smelly shoes";
            float blockChance = 0;
            bitmapEquipment = BitmapEquipment.LEATHERCHEST;
        }



        private void createLegArmor(MaterialType materialType) {

            switch (materialType) {

                case LEATHER:
                    createLeatherLegs();
                    break;
                case CHAIN:
                    createChainLegs();
                    break;
                case IRON:
                    createIronLegs();
                    break;
            }
        }
    private void createChestArmor(MaterialType materialType) {

        switch (materialType) {

            case LEATHER:
                createLeatherChest();
                break;
            case CHAIN:
                createChainChest();
                break;
            case IRON:
                createIronChest();
                break;
        }
    }
    private void createHeadArmor(MaterialType materialType){

        switch(materialType){

            case LEATHER:
                createLeatherHelmet();
                break;
            case CHAIN:
                createChainHelmet();
                break;
            case IRON:
                createIronHelmet();
                break;
        }




    }



    @Override
    public void processClick(){;

     //   System.out.println("i am da internet");

    }







}
