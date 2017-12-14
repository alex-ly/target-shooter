package com.example.mre_0.csci4100finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MrE_0 on 2017-12-11.
 *
 * android:background="@drawable/background"
 * app:srcCompat="@drawable/waiting"

 */

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }



    public void check(View v){
        //String NAME = "Gayanath";
        EditText et=(EditText)findViewById(R.id.editText2);
        String user=et.getText().toString();
        Intent intent= new Intent();

        UserDBHelper userDBHelper=new UserDBHelper(this);
        ArrayList<String> users=userDBHelper.getAllUsers();
        boolean exists=false;

        for (int i=0;i<users.size();i++){
            if(users.get(i).equals(user)){
                intent.putExtra("MainMenuFinalProject", "Login success");
                intent.putExtra("name", user);
                exists=true;
                break;

            }
        }

        if(!exists){
            //Log.i("Lab3", @string/fail);
            //intent.putExtra("MainMenuFinalProject", "Username not Recognized");
            Toast.makeText(getApplicationContext(), "Username not recognized", Toast.LENGTH_SHORT).show();

        } else{
            setResult(1, intent);
            finish();

        }

        //Log.i("Lab3","Login success");
    }
}
