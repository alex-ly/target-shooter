package com.example.mre_0.csci4100finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MrE_0 on 2017-11-14.
 */

public class ScoreArrayAdapter extends ArrayAdapter<Score> {
    private List<Score> data;
    private Context context;

    public ScoreArrayAdapter(Context context, List<Score> data){
        super(context, R.layout.score_item, data);
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Score score = data.get(position);

        Log.i("final project", "Showing - " + score);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.score_item, parent, false);
        }

        TextView lblRank = (TextView)reusableView.findViewById(R.id.lblRank);
        lblRank.setText(position+1);

        TextView lblName = (TextView)reusableView.findViewById(R.id.lblName);
        lblName.setText(score.getName());

        TextView lblScore = (TextView)reusableView.findViewById(R.id.lblScore);
        lblScore.setText(String.valueOf(score.getScore()));

        return reusableView;
    }

}
