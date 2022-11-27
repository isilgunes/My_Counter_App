package com.example.my_counter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h.postDelayed(new Runnable() {  //uygulamayı her baslattıgımızda karsımıza splash screen cıkması icin gereken kod.
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,nextActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}