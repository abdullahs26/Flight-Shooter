package com.example.flightshooter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.flightshooter.GameView.screenRatioX;
import static com.example.flightshooter.GameView.screenRatioY;

public class Fly {
    public boolean isFlying = false;
    int x;
    int y;
    int width;
    int height;
    int counter = 0;

    Bitmap flight,flight_2,flight_3,flight_4,flight_5,flight_6,flight_7,flight_8;


    Fly(int screenY, Resources res) {
        flight = BitmapFactory.decodeResource(res, R.drawable.helicopter_1);
        flight_2 = BitmapFactory.decodeResource(res, R.drawable.helicopter_2);
        flight_3 = BitmapFactory.decodeResource(res, R.drawable.helicopter_3);
        flight_4 = BitmapFactory.decodeResource(res, R.drawable.helicopter_4);
        flight_5 = BitmapFactory.decodeResource(res, R.drawable.helicopter_5);
        flight_6 = BitmapFactory.decodeResource(res, R.drawable.helicopter_6);
        flight_7 = BitmapFactory.decodeResource(res, R.drawable.helicopter_7);
        flight_8 = BitmapFactory.decodeResource(res, R.drawable.helicopter_8);

        width = flight.getWidth();
        height = flight.getHeight();

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        flight = Bitmap.createScaledBitmap(flight, width, height, false);
        flight_2 = Bitmap.createScaledBitmap(flight_2, width, height, false);
        flight_3 = Bitmap.createScaledBitmap(flight_3, width, height, false);
        flight_4 = Bitmap.createScaledBitmap(flight_4, width, height, false);
        flight_5 = Bitmap.createScaledBitmap(flight_5, width, height, false);
        flight_6 = Bitmap.createScaledBitmap(flight_6, width, height, false);
        flight_7 = Bitmap.createScaledBitmap(flight_7, width, height, false);
        flight_8 = Bitmap.createScaledBitmap(flight_8, width, height, false);


        // starts the flight at the center of the screen
        y = screenY / 2;

        x = (int) (64 * screenRatioX);

    }

    Bitmap getFly() {

        if (counter == 0) {
            counter++;
            return flight;
        }
        else if (counter == 1) {
            counter++;
            return flight_2;
        }
        else if (counter == 2) {
            counter++;
            return flight_3;
        }

        else if (counter == 3) {
            counter++;
            return flight_4;
        }

        else if (counter == 4) {
            counter++;
            return flight_5;
        }

        else if (counter == 5) {
            counter++;
            return flight_6;
        }

        else if (counter == 6) {
            counter++;
            return flight_7;
        }

        else {
            counter=0;
            return flight_8;
        }

    }
}
