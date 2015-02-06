package com.example.thoma_000.kohlersclassthings;

import android.graphics.Bitmap;

/**
 * Created by thoma_000 on 1/28/2015.
 */
public class Internet extends DrawableObject {

        public Internet (Bitmap bitmap, float xPos, float yPos, int width, int height) {
            super(bitmap, xPos, yPos, width, height);
        }
        @Override
        public void processClick(){

            System.out.println("i am da internet");

        }






    }
