package com.ott.playtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.ott.playserver.IPlayAidlInterface;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private TextureView textureView;
    private Surface surface;

    private Button play_aidl,play_broadcast;

    private IPlayAidlInterface iPlayAidlInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureView = findViewById(R.id.texture);
        textureView.setSurfaceTextureListener(this);//设置监听函数 重写4个方法

        Intent intent = new Intent();
        // 服务端AndroidManifest.xml文件该Service所配置的action
        intent.setAction("com.ott.playserver");
        // Service所在的包名
        intent.setPackage("com.ott.playserver");
        bindService(intent, new ConnectCallBack(), Context.BIND_AUTO_CREATE);


        play_aidl = findViewById(R.id.play_aidl);
        play_broadcast = findViewById(R.id.play_broadcast);

        play_aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (iPlayAidlInterface != null) {
                            try {   //此处传递真正的参数
                                iPlayAidlInterface.play(surface);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, 500); //延时1s执行
            }
        });

        play_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mIntent = new Intent("com.inspur.play");
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("surface", surface);// 序列化
                        mIntent.putExtras(bundle);// 发送数据
                        //发送广播
                        sendBroadcast(mIntent);
                    }
                }, 500);
            }
        });

    }


    class ConnectCallBack implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPlayAidlInterface = IPlayAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPlayAidlInterface = null;
        }
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        surface = new Surface(surfaceTexture);

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }
}
