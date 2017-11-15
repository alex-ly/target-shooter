package com.example.mre_0.csci4100finalproject;

/**
 * Created by MrE_0 on 2017-11-14.
 */

public class Score {
    private String name;
    private float score;
    private long id;

    public Score(String name, float score){
        setName(name);
        setScore(score);
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
