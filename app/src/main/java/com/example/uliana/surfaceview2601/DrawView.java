package com.example.uliana.surfaceview2601;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
private DrawThread drawThread;
    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(),getContext());
        drawThread.run();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.stopDrawing();
        boolean retry = true;
        while (retry){
            try {
                drawThread.join();
                retry=false;
            }catch (InterruptedException e){

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.getTouch(event.getX(), event.getY(), 0);
        return super.onTouchEvent(event);
    }
}
