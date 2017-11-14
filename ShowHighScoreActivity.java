package com.example.mre_0.csci4100finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrE_0 on 2017-11-14.
 */

public class ShowHighScoreActivity extends AppCompatActivity {

    private ScoreArrayAdapter scoreArrayAdapter;
    private ListView listView;
    private ScoreDBHelper scoreDBHelper;
    private ArrayList<Score> scores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);

        listView=(ListView)findViewById(R.id.list);
        scoreDBHelper=new ScoreDBHelper(this);
        updateScore();
        addScore();
    }

    public void updateScore(){
        scores=scoreDBHelper.getAllScores();
        scoreArrayAdapter=new ScoreArrayAdapter(this, scores);
        listView.setAdapter(scoreArrayAdapter);
    }

    public void deleteScore(Score score){
        scores.remove(score);
        scoreDBHelper.deleteScore(score.getId());
        updateScore();
    }

    public void addScore(){
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        float playerScore=intent.getFloatExtra("score",0.0f);
        Score score=new Score(name, playerScore);
        if(scores.size()<10){
            scores.add(score);
            scoreDBHelper.createScore(name, playerScore);
        }else{
            for(int i=0;i<10;i++){
                if(playerScore>scores.get(i).getScore()){//if the score is in the top 10
                    scores.add(score);
                    scoreDBHelper.createScore(name, playerScore);
                    updateScore();
                    deleteScore(scores.get(scores.size()-1));
                    break;
                }
            }
        }
        updateScore();
    }


    public void close(View view){
        finish();
    }

}
