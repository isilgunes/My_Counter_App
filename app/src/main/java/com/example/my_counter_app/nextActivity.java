package com.example.my_counter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.os.Bundle;

public class nextActivity extends AppCompatActivity {


    TextView value;
    Button plus,minus;
    Button ayar;
    int counter=0,upLimit=10,dLimit=0;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Vibrator vibrator=null;

    MediaPlayer mediaPlayer=null;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            counter+=5;
            value.setText(String.valueOf(counter));
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            counter-=5;
            value.setText(String.valueOf(counter));
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        value=(TextView) findViewById(R.id.value);
        plus=(Button) findViewById(R.id.arttir);
        minus=(Button) findViewById(R.id.azalt);
        ayar=(Button) findViewById(R.id.ayarlar);

        Context context=getApplicationContext();
        sharedPreferences= context.getSharedPreferences(context.getPackageName()
                ,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer=MediaPlayer.create(context,R.raw.bildirim);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<upLimit)
                    counter++;
                else{
                    vibrator.vibrate(1000);
                    Log.d("******","Telefon titreşti");
                    mediaPlayer.start();
                }
                value.setText(String.valueOf(counter));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter>dLimit)
                    counter--;
                else{
                    vibrator.vibrate(1000);
                    Log.d("******","Telefon titreşti");
                    mediaPlayer.start();
                }
                value.setText(String.valueOf(counter));
            }
        });

        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(nextActivity.this,AyarlarActivity.class);
                startActivity(i);


            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        upLimit=sharedPreferences.getInt("UpperLimit",10);
        dLimit=sharedPreferences.getInt("DLimit",0);

    }
}