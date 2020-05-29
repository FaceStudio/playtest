package com.ott.playtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;


public class PlayWindow extends FrameLayout {

    private SurfaceView surfaceView;
    private SurfaceHolder holder;

    public PlayWindow(Context context) {
        super(context);
        this.mcontext = context;
        initViews();
    }

    public PlayWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;
        initViews();
    }

    public PlayWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mcontext = context;
        initViews();
    }


    private Context mcontext;


    private void initViews(){
        View view = LayoutInflater.from(mcontext).inflate(R.layout.play_window,this,true);
        surfaceView = view.findViewById(R.id.surface_view1);

        holder = surfaceView.getHolder();

        Intent mIntent = new Intent("com.inspur.play");
        Bundle bundle = new Bundle();
        bundle.putParcelable("surface", holder.getSurface());// 序列化
        mIntent.putExtras(bundle);// 发送数据
        //发送广播
        mcontext.sendBroadcast(mIntent);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mIntent = new Intent("com.inspur.play");
                Bundle bundle = new Bundle();
                bundle.putParcelable("surface", holder.getSurface());// 序列化
                mIntent.putExtras(bundle);// 发送数据
                //发送广播
                mcontext.sendBroadcast(mIntent);
            }
        }, 500);



    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }


}
