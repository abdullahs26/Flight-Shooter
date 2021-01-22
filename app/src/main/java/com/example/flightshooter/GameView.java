package com.example.flightshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean pause;
    private int screenX;
    private int screenY;
    public static float screenRatioX;
    public static float screenRatioY;
    private Paint paint;
    private Background background_1;
    private Background background_2;
    private Fly fly;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;


        background_1 = new Background(screenX, screenY, getResources());
        background_2 = new Background(screenX, screenY, getResources());

        fly = new Fly(screenY, getResources());

        background_2.x = screenX;

        paint = new Paint();
    }

    @Override
    public void run() {
        while (pause != true){
            update();
            draw();
            sleep();
        }
    }


    private void update(){
        background_1.x -= 10 * screenRatioX;
        background_2.x -= 10 * screenRatioX;

        if(background_1.x + background_1.background.getWidth() < 0 ){
            background_1.x = screenX;
        }

        if(background_2.x + background_2.background.getWidth() < 0) {
            background_2.x = screenX;
        }

        if (fly.isFlying)
            fly.y -= 30 * screenRatioY;
        else
            fly.y += 30 * screenRatioY;

        if (fly.y < 0)
            fly.y = 0;

        if (fly.y >= screenY - fly.height)
            fly.y = screenY - fly.height;

    }

    private void draw(){

        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background_1.background, background_1.x, background_1.y, paint);
            canvas.drawBitmap(background_2.background, background_2.x, background_2.y, paint);


            canvas.drawBitmap(fly.getFly(), fly.x, fly.y, paint);
            getHolder().unlockCanvasAndPost(canvas);

        }

    }



    private void sleep(){
        try{
            thread.sleep(17);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getX() < screenX/2)
                fly.isFlying = true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP){
            fly.isFlying = false;
        }

        return true;
    }

    public void resume() {
        pause = false;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            pause = true;
            thread.join();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }

    }

}
