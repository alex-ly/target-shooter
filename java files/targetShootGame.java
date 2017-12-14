package com.example.mre_0.csci4100finalproject;

/**
 * Created by MrE_0 on 2017-12-11.
 */

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/*
* This portion of code was prepared by Mohammad Maaz Ahmed
* This is the basic gameplay of the target clicking game.
* This game has the user click a target given 5 seconds.
* Each hit (click) gives the player 100 paints, plus the
* amount of time left over from the 5 seconds to hit the target.
* The Target gets smaller on successive hits
* Each time the target will be 90% of its previous size, in other words
* the circle is decaying by 10% on each hit, irrespective of time.
* */

public class targetShootGame extends AppCompatActivity {

    double radius = 150;
    MediaPlayer music;
    int score = 0;
    int remainingTime;

    private static final int GAME_CODE=3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_shoot_game);
        music = MediaPlayer.create(this, R.raw.gamemusic);
        music.start();
        music.setLooping(true);

    }

    public void targetMiss (){
        /*  I recommend making an alternative screen with 2 buttons, one for 'Play Again' and
         *  another for 'View High Scores' which would connect to High Scores java file*/
        music.stop();
        Toast.makeText(getApplicationContext(), "Sorry Game Over", Toast.LENGTH_SHORT).show();
        Intent callingIntent=getIntent();
        Log.i("Score:", String.valueOf(score));
        //callingIntent.putExtra("name", callingIntent.getStringExtra("name"));
        callingIntent.putExtra("score", score*1.0f);

        setResult(GAME_CODE, callingIntent);
        finish();

    }

    CountDownTimer countDownTimer = new CountDownTimer(6000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            TextView timer = (TextView) findViewById(R.id.timer);
            timer.setText("Time Left: " + millisUntilFinished/1000 + " Seconds" + "\t Score: " + score);
            remainingTime = (int) millisUntilFinished/1000;
        }
        @Override
        public void onFinish() {
            TextView timer = (TextView) findViewById(R.id.timer);
            timer.setText("Time Left: 0 Seconds" + "\t Score: " + score);
            targetMiss();
        }
    }.start();

    public void targetHit (View view){

        ImageView target = (ImageView) findViewById(R.id.target);
        target.requestLayout();

        radius = radius*0.9;
        int dpradius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int)radius, getResources().getDisplayMetrics());
        target.getLayoutParams().height=dpradius;
        target.getLayoutParams().width=dpradius;

        Random rand = new Random();

        DisplayMetrics dispMet = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dispMet);
        int height = dispMet.heightPixels;
        int width = dispMet.widthPixels;

        int dpoffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        int xTransdp = rand.nextInt((width-dpradius)+1);
        int yTransdp = rand.nextInt((height-dpradius-dpoffset)+1);

        target.setX(xTransdp);
        target.setY(yTransdp);

        score = score + 100 + remainingTime;

        countDownTimer.cancel();
        countDownTimer.start();



    }

    public void targetMiss (View view){
        /*  I recommend making an alternative screen with 2 buttons, one for 'Play Again' and
         *  another for 'View High Scores' which would connect to High Scores java file*/
        music.stop();
        Toast.makeText(getApplicationContext(), "Sorry Game Over", Toast.LENGTH_SHORT).show();
        Intent callingIntent=getIntent();
        Log.i("Score:", String.valueOf(score));
        //callingIntent.putExtra("name", callingIntent.getStringExtra("name"));
        callingIntent.putExtra("score", score*1.0f);

        setResult(GAME_CODE, callingIntent);
        finish();

    }


}