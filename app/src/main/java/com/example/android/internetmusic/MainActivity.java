package com.example.android.internetmusic;

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button buttonStart,buttonStop;
    Boolean isConnectionExist=false;
    MobileInternetConnectionDetector cd;
    String AudioURL="http://feelthetour.com/Suja/Song1.mp3";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart=(Button)findViewById(R.id.button1);
        buttonStop=(Button)findViewById(R.id.button2);

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        cd=new MobileInternetConnectionDetector(getApplicationContext());

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConnectionExist=cd.checkMobileInternetConn();
                try {
                    mediaPlayer.setDataSource(AudioURL);
                    mediaPlayer.prepare();
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }catch (SecurityException e){
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(isConnectionExist){
                    ShowAlertDialog(MainActivity.this,"Internet Connection","Your Device has Internet",true);
                    mediaPlayer.start();
                }
                else{
                    ShowAlertDialog(MainActivity.this,"No Internet Connection","Your Device doesn't have internet connection",false);
                }


            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }

    public void ShowAlertDialog(Context context,String title,String  message,Boolean status){
        AlertDialog alertDialog= new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

       /* alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int x=5;
            }
        }); */
        alertDialog.show();
    }
}
