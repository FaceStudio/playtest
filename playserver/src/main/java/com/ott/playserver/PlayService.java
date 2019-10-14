package com.ott.playserver;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.Surface;

public class PlayService extends Service {

    String url_hls = "http://10.255.68.222:8090/icdn-srm/live.m3u8?st=GP19PnucHwp3egExRpeEAQ&e=1569576445&userid=test1111&folder=2&shifttime=0&channelid=cctv2-h&deviceid=&devicename=__Hi3798MV310&devicetype=0";

    private MediaPlayer mMediaPlayer;

    public PlayService() {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBindler();
    }

    class MyBindler extends IPlayAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void play(Surface surface) throws RemoteException {
            //具体实现功能的地方
            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url_hls));
                mMediaPlayer.setSurface(surface);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}

