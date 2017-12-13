package com.example.mre_0.csci4100finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int login_code = 1;
    static final int create_code = 2;

    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void about(View source){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    public void login(View source){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivityForResult(intent,login_code);
    }

    public void create(View source){
        Intent intent=new Intent(this, CreateActivity.class);
        startActivityForResult(intent, create_code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result){
        super.onActivityResult(requestCode,resultCode,result);
        if (requestCode == login_code || requestCode==create_code){
            String msg=result.getStringExtra("MainMenuFinalProject");
            Log.i("MainMenuFinalProject",msg);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(this, targetShootGame.class);
            name=intent.getStringExtra("name");
            //intent.putExtra("name", name);
            startActivity(intent);

        }else{
            Intent intent=new Intent(this, ShowHighScoreActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", result.getFloatExtra("score", 0.0f));
            startActivity(intent);
        }
    }
}
