package com.example.robo;
import java.util.Date;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    VideoView videoView;
    SensorManager sensorManager;
    Sensor sensor;
    boolean runing=false;
    int ref=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVisibility(View.INVISIBLE);

        sensorManager=(SensorManager)getSystemService(Service.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);



        //
        //videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.invento);
        //videoView.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0]<450 && runing==false){
            videoView.setVisibility(View.VISIBLE);
            runing=true;
            if(ref==1) {
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
                videoView.start();


            }
            else if(ref==2)
            {
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.zeera);
                videoView.start();


            }
            else if(ref==3)
            {
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.david);
                videoView.start();

            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sun);
                    videoView.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            runing=false;
                            videoView.setVisibility(View.INVISIBLE);
                            if(ref==3)
                            {
                                ref=0;
                            }
                            ref++;

                        }
                   },30000);


                }
            },10000);

        }





    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
