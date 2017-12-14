package com.example.mre_0.csci4100finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by MrE_0 on 2017-12-11.
 */

public class AboutActivity extends AppCompatActivity {
    private String id="1NoVD9b-gJU3Im4TnSVhX6tusF4EsBo1I8hzScXhIoTs";
    private String buffer="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        //download();
        //TextView tv=(TextView)findViewById(R.id.textView);
        //tv.setText(buffer);

    }
    /*

    private void download(){
        OutputStream os=new ByteArrayOutputStream();
        driveService.files()

    }
    */

    public void Finish(View view){
        finish();
    }

}
