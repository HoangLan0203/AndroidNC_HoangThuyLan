package com.example.bai2_1209;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Button btn1,btn2, btn3;
    private final boolean isRunningThread1= true;
    private final boolean isRunningThread2= true;
    private final boolean isRunningThread3= true;
    private Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1= findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        tv3= findViewById(R.id.tv3);
        btn1= findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        startThreads();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void startThreads(){
        //thread 1: random 50-100
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                if (isRunningThread1){
                    Random random=new Random();
                    int n=random.nextInt(51)+50;
                    tv1.setText(String.valueOf(n));
                }
                handler.postDelayed(this, 1000);
            }
        },0);
        final int[] oddNumber={1};
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunningThread2){
                    tv2.setText(String.valueOf(oddNumber[0]));
                    oddNumber[0]+=2;
                }
                handler.postDelayed(this, 2500);
            }
        },0);
        final int[] number={0};
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunningThread3){
                    tv3.setText(String.valueOf(number[0]));
                    number[0]++;
                }
                handler.postDelayed(this, 2000);
            }
        }, 0);
    }
}