package com.example.uliana.surfaceview2601;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean drawing = true;
    float x ,y ;
    float r = 0;

    public DrawThread(SurfaceHolder surfaceHolder, Context context) {
        this.surfaceHolder= surfaceHolder;
    }

    public void stopDrawing(){
        drawing=false;
    }

    public void getTouch (float x, float y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
    }


    @Override
    public void run() {

       if (drawing){
           Canvas canvas = surfaceHolder.lockCanvas();


           if (canvas!=null){
               try {
                   Paint paint = new Paint();
                   paint.setStyle(Paint.Style.FILL);
                   paint.setColor(Color.BLUE);
                   canvas.drawPaint(paint);

                   paint.setColor(Color.YELLOW);
                   paint.setAntiAlias(true);
                   canvas.drawCircle(x,y,r,paint);


               }
               finally {
                   surfaceHolder.unlockCanvasAndPost(canvas);
               }
           }
           r+=5;
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e){
               e.printStackTrace();
           }

       }
    }
}

