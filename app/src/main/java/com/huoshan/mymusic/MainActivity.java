package com.huoshan.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.huoshan.mymusic.databinding.ActivityMainBinding;
import com.huoshan.myplayer.WlTimeInfoBean;
import com.huoshan.myplayer.listener.WlOnCompleteListener;
import com.huoshan.myplayer.listener.WlOnErrorListener;
import com.huoshan.myplayer.listener.WlOnLoadListener;
import com.huoshan.myplayer.listener.WlOnParparedListener;
import com.huoshan.myplayer.listener.WlOnPauseResumeListener;
import com.huoshan.myplayer.listener.WlOnTimeInfoListener;
import com.huoshan.myplayer.log.MyLog;
import com.huoshan.myplayer.muteenum.MuteEnum;
import com.huoshan.myplayer.opengl.WlGLSurfaceView;
import com.huoshan.myplayer.player.WlPlayer;
import com.huoshan.myplayer.util.WlTimeUtil;

public class MainActivity extends AppCompatActivity {

    private WlPlayer wlPlayer;
    private TextView tvTime;
    private WlGLSurfaceView wlGLSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = findViewById(R.id.tv_time);
        wlGLSurfaceView = findViewById(R.id.wlglsurfaceview);

        // 申请系统权限
        MoveFileFromRaw2Sdcard.initPermissions(this);

        wlPlayer = new WlPlayer();
        wlPlayer.setWlGLSurfaceView(wlGLSurfaceView);
        wlPlayer.setWlOnParparedListener(new WlOnParparedListener() {
            @Override
            public void onParpared() {
                MyLog.d("准备好了，可以开始播放声音了");
                wlPlayer.start();
            }
        });
        wlPlayer.setWlOnLoadListener(new WlOnLoadListener() {
            @Override
            public void onLoad(boolean load) {
                if(load)
                {
                    MyLog.d("加载中...");
                }
                else
                {
                    MyLog.d("播放中...");
                }
            }
        });

        wlPlayer.setWlOnPauseResumeListener(new WlOnPauseResumeListener() {
            @Override
            public void onPause(boolean pause) {
                if(pause)
                {
                    MyLog.d("暂停中...");
                }
                else
                {
                    MyLog.d("播放中...");
                }
            }
        });

        wlPlayer.setWlOnTimeInfoListener(new WlOnTimeInfoListener() {
            @Override
            public void onTimeInfo(WlTimeInfoBean timeInfoBean) {
//                MyLog.d(timeInfoBean.toString());
                Message message = Message.obtain();
                message.what = 1;
                message.obj = timeInfoBean;
                handler.sendMessage(message);

            }
        });

        wlPlayer.setWlOnErrorListener(new WlOnErrorListener() {
            @Override
            public void onError(int code, String msg) {
                MyLog.d("code:" + code + ", msg:" + msg);
            }
        });

        wlPlayer.setWlOnCompleteListener(new WlOnCompleteListener() {
            @Override
            public void onComplete() {
                MyLog.d("播放完成了");
            }
        });
    }

    public void begin(View view) {
        wlPlayer.setSource("/sdcard/Music/gaoqing/001.mp4");
        //wlPlayer.setSource("/mnt/shared/Other/testvideo/楚乔传第一集.mp4");
//        wlPlayer.setSource("http://mpge.5nd.com/2015/2015-11-26/69708/1.mp3");
//        wlPlayer.setSource("http://ngcdn004.cnr.cn/live/dszs/index12.m3u8");
        wlPlayer.parpared();
    }

    public void pause(View view) {

        wlPlayer.pause();

    }

    public void resume(View view) {
        wlPlayer.resume();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1)
            {
                WlTimeInfoBean wlTimeInfoBean = (WlTimeInfoBean) msg.obj;
                tvTime.setText(WlTimeUtil.secdsToDateFormat(wlTimeInfoBean.getTotalTime(),
                        wlTimeInfoBean.getTotalTime())
                    + "/" + WlTimeUtil.secdsToDateFormat(wlTimeInfoBean.getCurrentTime(),
                        wlTimeInfoBean.getTotalTime()));

            }
        }
    };

    public void stop(View view) {
        wlPlayer.stop();
    }

    public void seek(View view) {
        wlPlayer.seek(215);
    }

    public void next(View view) {
        //wlPlayer.playNext("/mnt/shared/Other/testvideo/楚乔传第一集.mp4");
    }
}