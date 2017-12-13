package com.example.mre_0.csci4100finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MrE_0 on 2017-12-12.
 */

public class CreateActivity extends AppCompatActivity {

    private UserDBHelper userDBHelper;
    private ArrayList<String> users;
    static final int create_code = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        userDBHelper=new UserDBHelper(this);
        updateUsers();
    }

    public void updateUsers(){
        users=userDBHelper.getAllUsers();
    }

    public void verifyUser(View view){
        boolean used=false;
        EditText txtName=(EditText)findViewById(R.id.txtName);
        String name=txtName.getText().toString();

        for (int i =0;i<users.size();i++){
            if(name.equals(users.get(i))){
                used=true;
                break;
            }
        }
        if(used){
            Toast.makeText(this, "Username taken", Toast.LENGTH_SHORT).show();

        }else{
            addUser(name);
        }

    }

    public void addUser(String name){
        users.add(name);
        userDBHelper.createUser(name);

        updateUsers();
        Intent intent=getIntent();
        intent.putExtra("name", name);
        intent.putExtra("MainMenuFinalProject", "Account created successfully");

        setResult(create_code, intent);
        finish();
    }

}
