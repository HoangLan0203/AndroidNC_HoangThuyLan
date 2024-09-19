package com.example.bai3_1209;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    EditText etDelay;
    Button btnStart, btnStop;
    Timer timer;
    TimerTask timerTask;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         timePicker = findViewById(R.id.timePicker);
         etDelay= findViewById(R.id.edDelay);
         btnStart= findViewById(R.id.btnStart);
         btnStop = findViewById(R.id.btnStop);
          timer = new Timer();
          btnStart.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  startAlarm();
              }
          });

          btnStop.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  stopAlarm();
              }
          });
    }
    public  void startAlarm() {
        int delay = Integer.parseInt(etDelay.getText().toString()) * 60 * 1000; // delay in millisecond
        //Create  new TimeTask
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Phát âm thanh báo thức
                        if (mediaPlayer == null) {
                            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.OnMyWay);
                            mediaPlayer.start();
                        } else {
                            mediaPlayer.start();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, delay);
    }
    public void stopAlarm(){
        if(timerTask!= null){
            timerTask.cancel();
            Toast.makeText(this,"Alarm Canceled",Toast.LENGTH_SHORT).show();
        }
        if (mediaPlayer !=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}