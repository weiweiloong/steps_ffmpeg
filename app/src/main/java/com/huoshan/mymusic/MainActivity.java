package com.huoshan.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.huoshan.mymusic.databinding.ActivityMainBinding;
import com.huoshan.myplayer.listener.WlOnParparedListener;
import com.huoshan.myplayer.log.MyLog;
import com.huoshan.myplayer.player.WlPlayer;

public class MainActivity extends AppCompatActivity {

    private WlPlayer wlPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wlPlayer = new WlPlayer();
        wlPlayer.setWlOnParparedListener(new WlOnParparedListener() {
            @Override
            public void onParpared() {
                MyLog.d("准备好了，可以开始播放声音了");
                wlPlayer.start();
            }
        });
    }

    public void begin(View view) {

        wlPlayer.setSource("http://mpge.5nd.com/2015/2015-11-26/69708/1.mp3");
        wlPlayer.parpared();
    }

    public void begin2(View view) {

        wlPlayer.setSource("http://mpge.5nd.com/2015/2015-11-26/69708/1.mp3");
        wlPlayer.parpared();
    }
}